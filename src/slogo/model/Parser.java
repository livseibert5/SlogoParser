package slogo.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.controller.Controller;

public class Parser {

  private RegexDetector regexDetector = new RegexDetector();
  private CommandFactory commandFactory = new CommandFactory();
  private Controller controller;
  //private Stack<String> commandStack;
  private Stack<Object> commandStack;
  //private Stack<Constant> argumentStack;
  private Stack<Object> argumentStack;
  private static final String RESOURCES_PACKAGE = "resources.languages.";
  private Turtle turtle;

  public Parser(Controller controller) {
    this.controller = controller;
    turtle = controller.getTurtleHandler().getTurtle();
    commandStack = new Stack<>();
    argumentStack = new Stack<>();
    regexDetector.addPatterns("English");
    regexDetector.addPatterns("Syntax");
  }

  public Parser(Turtle turtle) {
    this.turtle = turtle;
    commandStack = new Stack<>();
    argumentStack = new Stack<>();
    regexDetector.addPatterns("English");
    regexDetector.addPatterns("Syntax");
  }

  public int parse(String command)
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    System.out.println(command);
    String[] commandComponents = command.split(" ");
    /*
    for (String commandComponent: commandComponents) {
      String commandType = regexDetector.getSymbol(commandComponent);
      if (commandType.equals("Constant")) {
        commandStack.push(commandComponent);
      } else {
        commandStack.push(commandType);
      }
      System.out.println(commandType);
    }*/
    int i = 0;
    while (i < commandComponents.length) {
      String commandType = regexDetector.getSymbol(commandComponents[i]);
      if (commandType.equals("ListStart")) {
        int openParenIndex = i;
        int parenCount = 1;
        while (parenCount != 0) {
          i++;
          commandType = regexDetector.getSymbol(commandComponents[i]);
          if (commandType.equals("ListStart")) {
            parenCount++;
          } else if (commandType.equals("ListEnd")) {
            parenCount--;
          }
        }
        List<String> commandBlock = Arrays.asList(Arrays.copyOfRange(commandComponents, openParenIndex + 1, i));
        StringBuilder block = new StringBuilder();
        for (String commandPiece: commandBlock) {
          block.append(commandPiece);
          block.append(" ");
        }
        block.deleteCharAt(block.length() - 1);
        CommandBlock codeBlock = new CommandBlock(block.toString());
        commandStack.push(codeBlock);
      } else if (commandType.equals("Constant")) {
        commandStack.push(new Constant(Integer.parseInt(commandComponents[i])));
      } else {
        commandStack.push(commandType);
      }
      System.out.println(commandStack.peek());
      i++;
    }
    parseCommandStack();
    return 0;
  }

  private void parseCommandStack()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + "English");
    while (!commandStack.isEmpty()) {
      if (!(commandStack.peek() instanceof Command) && !(commandStack.peek() instanceof String)) {
        argumentStack.push(commandStack.pop());
      } else {
        Object command = commandStack.pop();
        List<Object> parameters = new ArrayList<>();
        int numParameters = commandFactory.determineNumberParameters((String) command);
        if (argumentStack.size() >= numParameters) {
          for (int i = 0; i < numParameters; i++) {
            parameters.add(argumentStack.pop());
          }
        }
        Command newCommand = (Command) commandFactory.createCommand((String) command, parameters);
        argumentStack.push(new Constant(
            (int) newCommand.execute(turtle)));
        // Object newCommand = commandFactory.createCommand(commandStack.pop(), )
        // pop command off command stack and determine how many arguments it takes
        // if the argument stack is greater than or equal to the number of arguments needed, create
        // a command object and pass it the two commands.
        // execute this new command object and push it on to the argument stack.
      }
    }
    if (!argumentStack.isEmpty()) {
      System.out.println(((Constant) argumentStack.pop()).getValue());
    }
  }

  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Parser parser = new Parser(new Controller());
    parser.parse("fd sum 10 sum 10 sum 10 sum 20 20");
  }
}
