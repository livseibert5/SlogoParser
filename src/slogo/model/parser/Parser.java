package slogo.model.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Expression;
import slogo.model.Turtle;
import slogo.model.UserDefinedCommand;

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
  private Stack<Object> poppedStack;
  private Stack<Object> argumentStack;
  private static final String RESOURCE_FOLDER = "slogo.model.resources.";
  ResourceBundle resources = ResourceBundle.getBundle("resources.languages.English");
  ResourceBundle controlCommands = ResourceBundle.getBundle(RESOURCE_FOLDER + "ControlCommands");
  ResourceBundle expressionFactoryTypes = ResourceBundle.getBundle(RESOURCE_FOLDER + "ExpressionFactory");
  private Turtle turtle;
  private double result;

  /**
   * When a Parser is instantiated on the front end it is passed a Controller so
   * that the front end an back end both have access to the same TurtleHandler and VariableHandler.
   *
   * @param controller controller for the program
   */
  public Parser(Controller controller) {
    this.controller = controller;
    turtle = controller.getTurtleHandler().getTurtle(1);
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
    poppedStack = new Stack<>();
    argumentStack = new Stack<>();
    result = 0;
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
    return (int) result;
    //return (int) ((Expression) argumentStack.pop()).getValue();
  }

  /**
   * Pushes Objects to the command stack to be executed.
   *
   * @param commandComponents list of command components to parse
   */
  private void createCommandStack(String[] commandComponents) {
    int index = commandComponents.length - 1;
    while (index >= 0) {
      String commandType = regexDetector.getSymbol(commandComponents[index]);
      //System.out.println(commandComponents[index]);
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
    } else if (commandType.equals("ListEnd")) {
      int endIndex = expressionFactory.findBeginningOfCommandBlock(index, commandComponents, regexDetector);
      List<String> commandList = Arrays.asList(Arrays.copyOfRange(commandComponents, endIndex + 1, index));
      commandStack.push(expressionFactory.makeCommandBlock(commandList));
      System.out.println("HERE" + expressionFactory.makeCommandBlock(commandList).toString());
      return endIndex;
    } else if (commandType.equals("Variable")) {
      commandStack.push(expressionFactory.makeVariable(commandComponents[index], controller.getVariableHandler()));
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
      if (commandStack.peek() instanceof String && resources.containsKey((String) commandStack.peek())) {
        Object command = commandStack.pop();
        poppedStack.push(command);
        System.out.println("command stack size: " + commandStack.size());
        printCommandStack(commandStack);
        System.out.println("popped stack size: " + poppedStack.size());
        printCommandStack(poppedStack);
        List<Object> args = new ArrayList<>();
        int numArgs = commandFactory.determineNumberParameters((String) command);
        if (controlCommands.containsKey((String) command)) {
          args.add(controller);
          numArgs--;
        }
        if (commandStack.size() >= numArgs) {
          for (int i = 0; i < numArgs; i++) {
            Object popped = commandStack.pop();
            args.add(popped);
          }
        }
        try {
          Command commandObj = (Command) commandFactory.createCommand((String) command, args);
          System.out.println("execution " + command);
          result = commandObj.execute(turtle);
          Constant constant = expressionFactory.makeConstant((int) result);
          poppedStack.pop();
          commandStack.push(constant);
          while (!poppedStack.isEmpty()) {
            commandStack.push(poppedStack.pop());
          }
        } catch (Exception e) {
          if (controlCommands.containsKey((String) command)) {
            for (int i = args.size() - 1; i >= 1; i--) {
              commandStack.push(args.get(i));
            }
          } else {
            for (int i = args.size() - 1; i >= 0; i--) {
              commandStack.push(args.get(i));
            }
          }
        }
      } else {
        poppedStack.push(commandStack.pop());
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
      System.out.println(argumentStack.peek());
      parameters.add(argumentStack.pop());
    }
    return parameters;
  }

  private void printCommandStack(Stack<Object> stack){
    Object[] arr;
    arr = stack.toArray(new Object[0]);
    System.out.println("Stack: " + Arrays.toString(arr));
  }


  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Parser parser = new Parser(new Controller());
    System.out.println(parser.parse("repeat 3 [ repeat 2 [ fd 1 rt 2 ] rt 40 ]"));
    //System.out.println();
    //System.out.println(parser.parse("make :random sum 1 random 100"));
    //System.out.println(parser.parse("fd :random"));
    //System.out.println(parser.parse("dotimes [ :t 360 ] [ fd 1 rt / sin :t 2 ]"));
    //System.out.println(parser.parse("dash"));
    //System.out.println(parser.parse("to dash [ :count ] [ repeat :count [ pu fd 4 pd fd 4 ] ]"));
    //System.out.println(parser.parse("dash 10"));
    //System.out.println(parser.parse("fd rt 100"));
  }
}
