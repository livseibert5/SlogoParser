package slogo.model;

public class Parser {

  RegexDetector regexDetector = new RegexDetector();

  public Parser() {
    regexDetector.addPatterns("English");
    regexDetector.addPatterns("Syntax");
  }

  public int parse(String command) {
    String[] commandComponents = command.split(" ");
    for (String commandComponent: commandComponents) {
      System.out.println(regexDetector.getSymbol(commandComponent));
    }
    return 0;
  }

  public static void main(String[] args) {
    Parser parser = new Parser();
    parser.parse("fd * greater? 3 5 100");
  }
}
