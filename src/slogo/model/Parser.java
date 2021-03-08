package slogo.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.controller.Controller;

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
  private static final String RESOURCES_PACKAGE = "resources.languages.";
  ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + "English");
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
    int index = 0;
    while (index < commandComponents.length) {
      String commandType = regexDetector.getSymbol(commandComponents[index]);
      if (resources.containsKey(commandType)) {
        commandStack.push(commandType);
      } else {
        index = handleNonCommandExpressionComponents(commandType, commandComponents, index);
      }
      index++;
    }
    parseCommandStack();
    return 0;
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
      int endIndex = findEndOfOuterParenthesesBlock(index, commandComponents);
      List<String> commandList = Arrays.asList(Arrays.copyOfRange(commandComponents, index + 1, findEndOfOuterParenthesesBlock(index, commandComponents)));
      commandStack.push(listToCommandBlock(commandList));
      return endIndex;
    } else if (commandType.equals("Variable")) {
      if (controller.getVariableHandler().getVariableWithName(commandComponents[index]) != -1) {
        commandStack.push(new Constant((int) controller.getVariableHandler().getVariableWithName(commandComponents[index])));
      } else {
        commandStack.push(new Variable(commandComponents[index]));
      }
    }
    return index;
  }

  /**
   * Determines the bounds of a code block to assist with running control commands.
   *
   * @param index starting index of code block
   * @param commandComponents array of commands to be parsed
   * @return end index of code block
   */
  private int findEndOfOuterParenthesesBlock(int index, String[] commandComponents) {
    int parenCount = 1;
    while (parenCount != 0) {
      index++;
      String commandType = regexDetector.getSymbol(commandComponents[index]);
      if (commandType.equals("ListStart")) {
        parenCount++;
      } else if (commandType.equals("ListEnd")) {
        parenCount--;
      }
    }
    return index;
  }

  /**
   * Turns a list of commands into a CommandBlock object to assist with running control commands.
   *
   * @param commandList list of commands to be put in the CommandBlock
   * @return new CommandBlock object containing all the commands as a String
   */
  private CommandBlock listToCommandBlock(List<String> commandList) {
    StringBuilder block = new StringBuilder();
    for (String commandPiece: commandList) {
      block.append(commandPiece);
      block.append(" ");
    }
    block.deleteCharAt(block.length() - 1);
    return new CommandBlock(block.toString());
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
      if (!(commandStack.peek() instanceof Command) && !(commandStack.peek() instanceof String)) {
        argumentStack.push(commandStack.pop());
      } else {
        Object command = commandStack.pop();
        List<Object> parameters = new ArrayList<>();
        int numParameters = commandFactory.determineNumberParameters((String) command);
        if (command.equals("MakeVariable")) {
          for (int i = 0; i < 2; i++) {
            parameters.add(argumentStack.pop());
          }
        } else if (argumentStack.size() >= numParameters) {
          for (int i = 0; i < numParameters; i++) {
            parameters.add(argumentStack.pop());
          }
        }
        if (command.equals("MakeVariable")) {
          parameters.add(0, controller);
        }
        Command newCommand = (Command) commandFactory.createCommand((String) command, parameters);
        argumentStack.push(new Constant((int) newCommand.execute(turtle)));
      }
    }
  }

  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Parser parser = new Parser(new Controller());
    //parser.parse("repeat 3 [ repeat 2 [ fd 1 rt 2 ] rt 40 ]");
    parser.parse("make :random sum 1 random 100");
    parser.parse("fd :random");
  }
}
