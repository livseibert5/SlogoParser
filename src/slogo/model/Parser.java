package slogo.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.controller.Controller;

public class Parser {

  private RegexDetector regexDetector = new RegexDetector();
  private CommandFactory commandFactory = new CommandFactory();
  private Controller controller;
  private Stack<String> commandStack;
  private Stack<String> argumentStack;
  private static final String RESOURCES_PACKAGE = "resources.languages.";

  public Parser(Controller controller) {
    this.controller = controller;
    regexDetector.addPatterns("English");
    regexDetector.addPatterns("Syntax");
  }

  public int parse(String command)
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    String[] commandComponents = command.split(" ");
    for (String commandComponent: commandComponents) {
      String commandType = regexDetector.getSymbol(commandComponent);
      if (commandType.equals("Constant")) {
        commandStack.push(commandComponent);
      } else {
        commandStack.push(commandType);
      }
      System.out.println(commandType);
      Object newCommand = commandFactory.createCommand(commandType);
    }
    parseCommandStack();
    return 0;
  }

  private void parseCommandStack() {
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + "English");
    while (!commandStack.isEmpty()) {
      if (!resources.containsKey(commandStack.peek())) {
        argumentStack.push(commandStack.pop());
      } else {
        // pop command off command stack and determine how many arguments it takes
        // if the argument stack is greater than or equal to the number of arguments needed, create
        // a command object and pass it the two commands.
        // execute this new command object and push it on to the argument stack.
      }
    }
  }

  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Parser parser = new Parser(new Controller());
    parser.parse("fd 50 bd 50");
  }
}
