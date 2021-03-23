package slogo.model.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.stream.Collectors;
import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.GroupBlock;
import slogo.model.Turtle;
import slogo.model.UserDefinedCommand;
import slogo.model.Value;
import slogo.model.backendexceptions.MathException;

/**
 * Parser takes in a user input String from the front end using the .parse function and determines
 * which functions to call and which inputs to pass them so that the program state is updates
 * appropriately.
 *
 * @author Livia Seibert
 */
public class Parser {

  private RegexDetector regexDetector = new RegexDetector();
  private final CommandFactory commandFactory = new CommandFactory();
  private final Controller controller;
  private ExpressionFactory expressionFactory;
  private Stack<Object> commandStack;
  private Stack<Object> poppedStack;
  private static final String RESOURCE_FOLDER = "slogo.model.resources.";
  private ResourceBundle resources;
  private final ResourceBundle expressionFactoryTypes = ResourceBundle
      .getBundle(RESOURCE_FOLDER + "ExpressionFactory");
  private List<Turtle> turtles;
  private double result;

  /**
   * When a Parser is instantiated on the front end it is passed a Controller so that the front end
   * an back end both have access to the same TurtleHandler and VariableHandler.
   *
   * @param controller controller for the program
   */
  public Parser(Controller controller) {
    this.controller = controller;
    resources = ResourceBundle.getBundle("resources.languages." + controller.getLanguage());
    turtles = controller.getTurtleHandler().getActiveTurtles();
    setUpParser();
  }

  /**
   * Initializes the essential variables for the parser.
   */
  private void setUpParser() {
    commandStack = new Stack<>();
    poppedStack = new Stack<>();
    result = 0;
    expressionFactory = new ExpressionFactory();
    regexDetector = new RegexDetector();
    regexDetector.addPatterns(controller.getLanguage());
    regexDetector.addPatterns("Syntax");
  }

  /**
   * Takes in user input from the front end and parses the String in order to execute its expected
   * behavior.
   *
   * @param command String with commands from IDE
   * @return integer result of the command
   * @throws ClassNotFoundException    class not found in command factory
   * @throws NoSuchMethodException     method not found in command factory
   * @throws InvocationTargetException can't invoke target
   * @throws InstantiationException    can't make new object from command factory
   * @throws IllegalAccessException    trying to make object in command factory without access
   */
  public int parse(String command)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MathException {
    updateLanguage();
    setUpParser();
    turtles = controller.getTurtleHandler().getActiveTurtles();
    String[] commandComponents = removeComments(command).split(" ");
    createCommandStack(commandComponents);
    parseCommandStack();
    return (int) result;
  }

  private void updateLanguage() {
    resources = ResourceBundle.getBundle("resources.languages." + controller.getLanguage());
    regexDetector.addPatterns(controller.getLanguage());
  }

  /**
   * Removes comments from the command so they aren't parsed.
   *
   * @param command command to remove comments from
   * @return command without comments
   */
  private String removeComments(String command) {
    String[] commandLines = command.split("\\n");
    List<String> newCommandLines = Arrays.asList(commandLines);
    newCommandLines.replaceAll(String::trim);
    newCommandLines = newCommandLines.stream()
        .filter(line -> !regexDetector.getSymbol(line.split(" ")[0]).equals("Comment")).collect(
            Collectors.toList());
    StringBuilder result = new StringBuilder();
    for (String line : newCommandLines) {
      result.append(line);
      result.append(" ");
    }
    result.deleteCharAt(result.length() - 1);
    return result.toString();
  }

  /**
   * Pushes Objects to the command stack to be executed.
   *
   * @param commandComponents list of command components to parse
   */
  private void createCommandStack(String[] commandComponents)
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    int index = commandComponents.length - 1;
    while (index >= 0) {
      String commandType = regexDetector.getSymbol(commandComponents[index]);
      if (resources.containsKey(commandType)) {
        commandStack.push(commandType);
      } else if (commandType.equals("Command")) {
        commandStack.push(commandComponents[index]);
      } else {
        index = handleNonCommandExpressionComponents(commandType, commandComponents, index);
      }
      index--;
    }
  }

  /**
   * Handles creation of Constants, CommandBlocks, and Variables.
   *
   * @param commandType       type of object to create
   * @param commandComponents list of command pieces
   * @param index             current index of commandComponents list
   * @return new index of commandComponents list
   */
  private int handleNonCommandExpressionComponents(String commandType, String[] commandComponents,
      int index)
      throws ClassNotFoundException, NoSuchMethodException, MathException, InstantiationException, IllegalAccessException, InvocationTargetException {
    GroupBlock groupBlock = new GroupBlock(controller);
    switch (commandType) {
      case "Constant" -> commandStack
          .push(expressionFactory.makeConstant(Double.parseDouble(commandComponents[index])));
      case "ListEnd" -> {
        int endIndex = expressionFactory
            .findBeginningOfCommandBlock(index, commandComponents, regexDetector);
        List<String> commandList = Arrays
            .asList(Arrays.copyOfRange(commandComponents, endIndex + 1, index));
        commandStack.push(expressionFactory.makeCommandBlock(commandList));
        return endIndex;
      }
      case "GroupEnd" -> {
        int endIndex = groupBlock.findIndex(index, commandComponents, regexDetector);
        List<String> groupList = Arrays
            .asList(Arrays.copyOfRange(commandComponents, endIndex + 1, index));
        if (commandStack.size() > 0) {
          groupList.add((String) commandStack.pop());
        }
        int result = parse(groupBlock.insertCommand(groupList));
        commandStack.push(expressionFactory.makeConstant(result));
        return endIndex;
      }
      case "Variable" -> commandStack.push(expressionFactory
          .makeVariable(commandComponents[index], controller.getVariableHandler()));
    }
    return index;
  }

  /**
   * Pops commands off of the commandStack, passes them their expected parameters, and runs them.
   *
   * @throws ClassNotFoundException    class not found in command factory
   * @throws NoSuchMethodException     method not found in command factory
   * @throws InvocationTargetException can't invoke target
   * @throws InstantiationException    can't make new object from command factory
   * @throws IllegalAccessException    trying to make object in command factory without access
   */
  private void parseCommandStack()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, MathException {
    while (!commandStack.isEmpty()) {
      if (expressionFactoryTypes.containsKey(commandStack.peek().getClass().getName())) {
        poppedStack.push(commandStack.pop());
      } else if (controller.getUserDefinedCommandHandler()
          .containsCommand((String) commandStack.peek())) {
        executeUserDefinedCommand();
      } else {
        executeSlogoCommand();
      }
    }
  }

  /**
   * Runs a user defined command with the values input at runtime.
   *
   * @throws ClassNotFoundException    class not found in command factory
   * @throws NoSuchMethodException     method not found in command factory
   * @throws InvocationTargetException can't invoke target
   * @throws InstantiationException    can't make new object from command factory
   * @throws IllegalAccessException    trying to make object in command factory without access
   */
  private void executeUserDefinedCommand()
      throws ClassNotFoundException, NoSuchMethodException, MathException, InstantiationException, IllegalAccessException, InvocationTargetException {
    Object command = commandStack.pop();
    poppedStack.push(command);
    UserDefinedCommand userCommand = controller.getUserDefinedCommandHandler()
        .getCommand((String) command);
    List<Object> parameters = generateParameters((String) command,
        userCommand.getNumberParameters());
    if (!hasCorrectUserDefinedArguments(parameters)) {
      resetArguments((String) command, parameters);
    } else {
      String newCommand = userCommand.generateCommand(parameters);
      Constant constant = expressionFactory.makeConstant(parse(newCommand));
      endCommand(constant);
    }
  }

  /**
   * Checks if the input for the user defined commands is of the correct type.
   *
   * @param parameters list of parameters for the command
   * @return true if the parameters are all values, false otherwise
   */
  private boolean hasCorrectUserDefinedArguments(List<Object> parameters) {
    for (Object param : parameters) {
      if (!(param instanceof Value)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Executes a slogo command.
   *
   * @throws ClassNotFoundException can't find class for given command type
   */
  private void executeSlogoCommand() throws ClassNotFoundException {
    Object command = commandStack.pop();
    poppedStack.push(command);
    int numArgs = commandFactory.determineNumberParameters((String) command);
    List<Object> args = generateParameters((String) command, numArgs);
    try {
      Command commandObj = (Command) commandFactory.createCommand((String) command, args);
      executeOnMultipleTurtles((String) command, commandObj);
      Constant constant = expressionFactory.makeConstant((int) result);
      endCommand(constant);
    } catch (Exception e) {
      resetArguments((String) command, args);
    }
  }

  /**
   * Executes a command for all active turtles.
   *
   * @param command    command name
   * @param commandObj instance of command to execute
   * @throws ClassNotFoundException    class not found in command factory
   * @throws NoSuchMethodException     constructor doesn't exist in command factory
   * @throws InvocationTargetException issue invoking constructor
   * @throws InstantiationException    issue instantiating command
   * @throws IllegalAccessException    illegal access to command
   * @throws MathException             illegal math command
   */
  private void executeOnMultipleTurtles(String command, Command commandObj)
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    if (command.equals("Tell") || command.equals("Ask") || command.equals("AskWith")) {
      result = commandObj.execute(new Turtle());
      turtles = controller.getTurtleHandler().getActiveTurtles();
    } else {
      turtles = controller.getTurtleHandler().getActiveTurtles();
      for (Turtle turtle : turtles) {
        result = commandObj.execute(turtle);
      }
    }
  }

  /**
   * Handles the rest of command stack after a command is executed.
   *
   * @param result constant containing return value of command execution
   */
  private void endCommand(Constant result) {
    poppedStack.pop();
    commandStack.push(result);
    while (!poppedStack.isEmpty()) {
      commandStack.push(poppedStack.pop());
    }
  }

  /**
   * Puts arguments back on the commandStack if they are not the right arguments for the command.
   *
   * @param command command attempted to execute
   * @param args    list of arguments given to command
   */
  private void resetArguments(String command, List<Object> args) throws ClassNotFoundException {
    int lowerBound = commandFactory.isControlCommand(command) ? 1 : 0;
    for (int i = args.size() - 1; i >= lowerBound; i--) {
      commandStack.push(args.get(i));
    }
  }

  /**
   * Creates the list of parameters for a new command object.
   *
   * @param command type of command to be created
   * @return list of parameters for command
   */
  private List<Object> generateParameters(String command, int numArgs)
      throws ClassNotFoundException {
    List<Object> args = new ArrayList<>();
    if (commandFactory.isControlCommand(command)) {
      args.add(controller);
      numArgs--;
    }
    if (commandStack.size() >= numArgs) {
      for (int i = 0; i < numArgs; i++) {
        Object popped = commandStack.pop();
        args.add(popped);
      }
    }
    return args;
  }
}
