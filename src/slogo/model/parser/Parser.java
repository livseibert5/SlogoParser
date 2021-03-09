package slogo.model.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.Expression;
import slogo.model.Turtle;

/**
 * Parser takes in a user input String from the front end using the .parse function
 * and determines which functions to call and which inputs to pass them so that the
 * program state is updates appropriately.
 *
 * @author Livia Seibert
 */
public class Parser {

  private RegexDetector regexDetector = new RegexDetector();
  private CommandFactory commandFactory = new CommandFactory();
  private Controller controller;
  private ExpressionFactory expressionFactory;
  private Stack<Object> commandStack;
  private Stack<Object> argumentStack;
  private static final String RESOURCE_FOLDER = "slogo.model.resources.";
  ResourceBundle resources = ResourceBundle.getBundle("resources.languages.English");
  ResourceBundle controlCommands = ResourceBundle.getBundle(RESOURCE_FOLDER + "ControlCommands");
  ResourceBundle expressionFactoryTypes = ResourceBundle.getBundle(RESOURCE_FOLDER + "ExpressionFactory");
  private Turtle turtle;

  /**
   * When a Parser is instantiated on the front end it is passed a Controller so
   * that the front end an back end both have access to the same TurtleHandler and VariableHandler.
   *
   * @param controller controller for the program
   */
  public Parser(Controller controller) {
    this.controller = controller;
    turtle = controller.getTurtleHandler().getTurtle();
    setUpParser();
  }

  /**
   * When a Parser is instantiated within a loop command, the command does not have access to
   * the controller. However, the command has a reference to the turtle to execute the command on,
   * so it can pass that turtle to the parser to parse the inner blocks of a loop.
   *
   * @param turtle turtle object to execute commands on
   */
  public Parser(Turtle turtle) {
    this.turtle = turtle;
    setUpParser();
  }

  /**
   * Initializes the essential variables for the parser.
   */
  private void setUpParser() {
    commandStack = new Stack<>();
    argumentStack = new Stack<>();
    expressionFactory = new ExpressionFactory();
    regexDetector.addPatterns("English");
    regexDetector.addPatterns("Syntax");
  }

  /**
   * Takes in user input from the front end and parses the String in order to execute its
   * expected behavior.
   *
   * @param command String with commands from IDE
   * @return integer result of the command
   * @throws ClassNotFoundException
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public int parse(String command)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    String[] commandComponents = command.split(" ");
    createCommandStack(commandComponents);
    parseCommandStack();
    return (int) ((Expression) argumentStack.pop()).getValue();
  }

  /**
   * Pushes Objects to the command stack to be executed.
   *
   * @param commandComponents list of command components to parse
   */
  private void createCommandStack(String[] commandComponents) {
    int index = 0;
    while (index < commandComponents.length) {
      String commandType = regexDetector.getSymbol(commandComponents[index]);
      System.out.println(commandComponents[index]);
      if (resources.containsKey(commandType)) {
        commandStack.push(commandType);
      } else {
        index = handleNonCommandExpressionComponents(commandType, commandComponents, index);
      }
      index++;
    }
  }

  /**
   * Handles creation of Constants and CommandBlocks.
   *
   * @param commandType type of object to create
   * @param commandComponents list of command pieces
   * @param index current index of commandComponents list
   * @return new index of commandComponents list
   */
  private int handleNonCommandExpressionComponents(String commandType, String[] commandComponents, int index) {
    if (commandType.equals("Constant")) {
      commandStack.push(expressionFactory.makeConstant(Integer.parseInt(commandComponents[index])));
    } else if (commandType.equals("ListStart")) {
      int endIndex = expressionFactory.findEndOfCommandBlock(index, commandComponents, regexDetector);
      List<String> commandList = Arrays.asList(Arrays.copyOfRange(commandComponents, index + 1, endIndex));
      commandStack.push(expressionFactory.makeCommandBlock(commandList));
      return endIndex;
    } else if (commandType.equals("Variable")) {
      commandStack.push(expressionFactory.makeVariable(commandComponents[index], controller.getVariableHandler()));
      System.out.println("command component " + commandComponents[index]);
    }
    return index;
  }

  /**
   * Pops commands off of the commandStack, passes them their expected parameters, and runs them.
   *
   * @throws ClassNotFoundException
   * @throws NoSuchMethodException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  private void parseCommandStack()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    while (!commandStack.isEmpty()) {
      if (expressionFactoryTypes.containsKey(commandStack.peek().getClass().getName())) {
        argumentStack.push(commandStack.pop());
      } else {
        Object command = commandStack.pop();
        List<Object> parameters = generateParameters((String) command, commandFactory.determineNumberParameters((String) command));
        Command newCommand = (Command) commandFactory.createCommand((String) command, parameters);
        argumentStack.push(expressionFactory.makeConstant((int) newCommand.execute(turtle)));
      }
    }
  }

  /**
   * Creates the list of parameters for a new command object.
   *
   * @param command type of command to be created
   * @param numParameters number of parameters command expects
   * @return list of parameters for command
   */
  private List<Object> generateParameters(String command, int numParameters) {
    List<Object> parameters = new ArrayList<>();
    if (controlCommands.containsKey(command)) {
      parameters.add(controller);
      numParameters--;
    }
    for (int i = 0; i < numParameters; i++) {
      parameters.add(argumentStack.pop());
    }
    return parameters;
  }

  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Parser parser = new Parser(new Controller());
    //System.out.println(parser.parse("repeat 3 [ repeat 2 [ fd 1 rt 2 ] rt 40 ]"));
    //System.out.println();
    //System.out.println(parser.parse("make :random sum 1 random 100"));
    //System.out.println(parser.parse("fd :random"));
    System.out.println(parser.parse("dotimes [ :t 360 ] [ fd 1 rt / sin :t 2 ]"));
  }
}
