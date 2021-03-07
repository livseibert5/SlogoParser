package slogo.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;
import slogo.controller.Controller;

public class Parser {

  private RegexDetector regexDetector = new RegexDetector();
  private CommandFactory commandFactory = new CommandFactory();
  private Controller controller;

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
      System.out.println(commandType);
      Object newCommand = commandFactory.createCommand(commandType);
    }
    return 0;
  }

  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Parser parser = new Parser(new Controller());
    parser.parse("fd 50 bd 50");
  }
}
