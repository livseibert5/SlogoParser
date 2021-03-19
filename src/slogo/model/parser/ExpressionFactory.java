package slogo.model.parser;

import java.util.List;
import slogo.model.CommandBlock;
import slogo.model.Constant;
import slogo.model.Variable;
import slogo.model.handlers.VariableHandler;

/**
 * ExpressionFactory creates Constants, CommandBlocks, and Variable's for the Parser to hide the
 * implementation details of the constructors of these classes.
 *
 * @author Livia Seibert
 */
public class ExpressionFactory {

  /**
   * Creates a new Constant object with the given value.
   *
   * @param value value for new constant object
   * @return Constant object with given value
   */
  public Constant makeConstant(int value) {
    return new Constant(value);
  }

  /**
   * Creates a new CommandBlock object from the given list of commands.
   *
   * @param commandList list of commands to turn into command block
   * @return CommandBlock object that contains given commands
   */
  public CommandBlock makeCommandBlock(List<String> commandList) {
    StringBuilder block = new StringBuilder();
    for (String commandPiece : commandList) {
      block.append(commandPiece);
      block.append(" ");
    }
    block.deleteCharAt(block.length() - 1);
    return new CommandBlock(block.toString());
  }

  /**
   * Retrieves the value of the variable as a constant if a variable already exists with the given
   * name, otherwise creates a new variable with the given name.
   *
   * @param name            name of variable
   * @param variableHandler variable handler for backend
   * @return new Variable with given name or Constant value of variable with given name
   */
  public Variable makeVariable(String name, VariableHandler variableHandler) {
    if (variableHandler.getVariableValueWithName(name) != -1) {
      return variableHandler.getVariableWithName(name);
    } else {
      return new Variable(name);
    }
  }

  /**
   * Determines where the end of the given command block is so that it can be turned into a
   * CommandBlock object.
   *
   * @param index             index of start of command block
   * @param commandComponents list of all of the command components
   * @param regexDetector     regex detector to determine if a bracket is a list ending bracket
   * @return index of end of command block
   */
  public int findBeginningOfCommandBlock(int index, String[] commandComponents,
      RegexDetector regexDetector) {
    int parenCount = 1;
    while (parenCount != 0) {
      index--;
      String commandType = regexDetector.getSymbol(commandComponents[index]);
      if (commandType.equals("ListEnd")) {
        parenCount++;
      } else if (commandType.equals("ListStart")) {
        parenCount--;
      }
    }
    return index;
  }
}
