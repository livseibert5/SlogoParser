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


  public GroupBlock(Controller controller) {
    RegexDetector regexDetector = new RegexDetector();
    regexDetector.addPatterns(controller.getLanguage());
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

  public String insertCommand(List<String> arguments) {

    List<String> flexArgs = new ArrayList<>(arguments);

    String command = arguments.get(0);

    int START_INDEX = 1;
    int MAX_CHANGE = 1;

    int count = START_INDEX;
    int change = 0;
    while(count < arguments.size()){
      if(change < MAX_CHANGE){
        change++;
        count++;
      }
      else if (change == MAX_CHANGE){
        flexArgs.add(count, command);
        change = 0;
        count++;
      }
    }

    return String.join(" ", flexArgs);
  }

}
