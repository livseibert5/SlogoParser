package slogo.model;

import java.util.List;
import java.util.ResourceBundle;

public class ExpressionFactory {

  private static final String RESOURCES_PACKAGE = ExpressionFactory.class.getPackageName() + ".resources.";
  ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + "ExpressionFactory");

  public Constant makeConstant(int value) {
    return new Constant(value);
  }

  public CommandBlock makeCommandBlock(List<String> commandList) {
    StringBuilder block = new StringBuilder();
    for (String commandPiece: commandList) {
      block.append(commandPiece);
      block.append(" ");
    }
    block.deleteCharAt(block.length() - 1);
    return new CommandBlock(block.toString());
  }

  public Object makeVariable(String name, VariableHandler variableHandler) {
    if (variableHandler.getVariableValueWithName(name) != -1) {
      return new Constant((int) variableHandler.getVariableValueWithName(name));
    } else {
      return new Variable(name);
    }
  }

  public int findEndOfCommandBlock(int index, String[] commandComponents, RegexDetector regexDetector) {
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
}
