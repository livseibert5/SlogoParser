package slogo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import slogo.controller.Controller;
import slogo.model.parser.CommandFactory;
import slogo.model.parser.RegexDetector;

/**
 * Class to handle unlimited parameters/grouping
 *
 * @author Rachel Luria
 */
public class GroupBlock {

  private final CommandFactory commandFactory;
  private final RegexDetector regexDetector;

  public GroupBlock(Controller controller) {
    commandFactory = new CommandFactory();
    regexDetector = new RegexDetector();
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

  public String insertCommand(List<String> arguments) throws ClassNotFoundException {

    List<String> flexArgs = new ArrayList<>(arguments);

    String command = arguments.get(0);
    String commandType = regexDetector.getSymbol(command);

    int START_INDEX = 1;
    int MAX_CHANGE = 1;

    int numArgs = findNumberArguments(commandType, command);

    int count = START_INDEX;
    int change = 0;
    boolean skip = false;
    while (count < flexArgs.size() - numArgs + START_INDEX) {

      if (flexArgs.get(count).equals("(")) {
        skip = true;
      }

      if (skip) {
        count++;
      } else if (change < MAX_CHANGE) {
        change++;
        count++;
      } else if (change == MAX_CHANGE) {
        flexArgs.add(count, command);
        change = 0;
        count++;
      }

      if (flexArgs.get(count - 1).equals(")")) {
        skip = false;
      }
    }
    return String.join(" ", flexArgs);
  }

  private int findNumberArguments(String commandType, String command)
      throws ClassNotFoundException {
    int numArgs = commandFactory.determineNumberParameters(commandType);
    if (commandFactory.isControlCommand(command)) {
      numArgs--;
    }
    return numArgs;
  }

}

