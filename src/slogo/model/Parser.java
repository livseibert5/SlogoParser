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
  private Stack<Object> commandStack;
  private Stack<Object> argumentStack;
  private static final String RESOURCES_PACKAGE = "resources.languages.";
  private Turtle turtle;

  public Parser(Controller controller) {
    this.controller = controller;
    turtle = controller.getTurtleHandler().getTurtle();
    setUpParser();
  }

  public Parser(Turtle turtle) {
    this.turtle = turtle;
    setUpParser();
  }

  private void setUpParser() {
    commandStack = new Stack<>();
    argumentStack = new Stack<>();
    regexDetector.addPatterns("English");
    regexDetector.addPatterns("Syntax");
  }

  public int parse(String command)
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    String[] commandComponents = command.split(" ");
    int i = 0;
    while (i < commandComponents.length) {
      String commandType = regexDetector.getSymbol(commandComponents[i]);
      if (commandType.equals("ListStart")) {
        int endParenIndex = findEndOfOuterParenthesesBlock(i, commandComponents);
        List<String> commandBlock = Arrays.asList(Arrays.copyOfRange(commandComponents, i + 1, findEndOfOuterParenthesesBlock(i, commandComponents)));
        i = endParenIndex;
        commandStack.push(listToCommandBlock(commandBlock));
      } else if (commandType.equals("Constant")) {
        commandStack.push(new Constant(Integer.parseInt(commandComponents[i])));
      } else {
        commandStack.push(commandType);
      }
      if (commandStack.peek() instanceof Constant) {
        System.out.println(((Constant) commandStack.peek()).getValue());
      } else {
        System.out.println(commandStack.peek());
      }
      i++;
    }
    parseCommandStack();
    return 0;
  }

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

  private CommandBlock listToCommandBlock(List<String> commandBlock) {
    StringBuilder block = new StringBuilder();
    for (String commandPiece: commandBlock) {
      block.append(commandPiece);
      block.append(" ");
    }
    block.deleteCharAt(block.length() - 1);
    return new CommandBlock(block.toString());
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
        argumentStack.push(new Constant((int) newCommand.execute(turtle)));
      }
    }
    if (!argumentStack.isEmpty()) {
      //System.out.println(((Constant) argumentStack.pop()).getValue());
    }
  }

  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Parser parser = new Parser(new Controller());
    parser.parse("repeat 3 [ repeat 2 [ fd 1 rt 2 ] rt 40 ]");
  }
}
