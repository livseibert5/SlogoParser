package slogo.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.controller.Controller;

public class Parser {

  private RegexDetector regexDetector = new RegexDetector();
  private CommandFactory commandFactory = new CommandFactory();
  private Controller controller;
  private Stack<String> commandStack;
  private Stack<Constant> argumentStack;
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
    }
    parseCommandStack();
    return 0;
  }

  private void parseCommandStack()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + "English");
    while (!commandStack.isEmpty()) {
      if (!resources.containsKey(commandStack.peek())) {
        argumentStack.push(new Constant(Integer.parseInt(commandStack.pop())));
      } else {
        String command = commandStack.pop();
        List<Constant> parameters = new ArrayList<>();
        int numParameters = commandFactory.determineNumberParameters(command);
        if (argumentStack.size() >= numParameters) {
          for (int i = 0; i < numParameters; i++) {
            parameters.add(argumentStack.pop());
          }
        }
        Command newCommand = (Command) commandFactory.createCommand(command, parameters);
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
      System.out.println(argumentStack.pop().getValue());
    }
  }

  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Parser parser = new Parser(new Controller());
    parser.parse("fd sum 10 sum 10 sum 10 sum 20 20");
  }
}
