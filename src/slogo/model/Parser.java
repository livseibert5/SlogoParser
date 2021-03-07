package slogo.model;

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

  public int parse(String command) {
    String[] commandComponents = command.split(" ");
    for (String commandComponent: commandComponents) {
      String commandType = regexDetector.getSymbol(commandComponent);
      System.out.println(commandType);
      //Command newCommand = commandFactory.createCommand(commandType);
    }
    return 0;
  }

  public static void main(String[] args) {
    Parser parser = new Parser(new Controller());
    parser.parse("fd sum sum sum sum 10 20 30 5 5");
  }
}
