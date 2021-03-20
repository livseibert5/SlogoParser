package slogo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import slogo.controller.Controller;
import slogo.model.parser.CommandFactory;
import slogo.model.parser.RegexDetector;

public class GroupBlock {

  private final ResourceBundle expressionFactoryTypes = ResourceBundle
      .getBundle(RESOURCE_FOLDER + "ExpressionFactory");
  private static final String RESOURCE_FOLDER = "slogo.model.resources.";
  private final CommandFactory commandFactory;
  private final RegexDetector regexDetector;
  private final ResourceBundle resources;

  public GroupBlock(Controller controller) {
    commandFactory = new CommandFactory();
    regexDetector = new RegexDetector();
    regexDetector.addPatterns(controller.getLanguage());
    this.resources = ResourceBundle.getBundle("resources.languages." + controller.getLanguage());
  }

  public int findIndex(int index, String[] commandComponents, RegexDetector regexDetector) {
    int parenCount = 1;
    while (parenCount != 0) {
      index--;
      String commandType = regexDetector.getSymbol(commandComponents[index]);
      if (commandType.equals("GroupEnd")) {
        parenCount++;
      } else if (commandType.equals("GroupStart")) {
        parenCount--;
      }
    }
    return index;
  }

  public String insertCommand(List<String> arguments)
      throws ClassNotFoundException {

    List<String> flexArgs = new ArrayList<>(arguments);

    String command = arguments.get(0);

    int count = 1;
    int change = 0;
    while(count < arguments.size()){
      if(change < 1){
        change++;
        count++;
      }
      else if (change == 1){
        flexArgs.add(count, command);
        change = 0;
        count++;
      }
    }

    return String.join(" ", flexArgs);
  }

}
