package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.CommandBlock;
import slogo.model.backendexceptions.MathException;
import slogo.model.parser.Parser;
import slogo.model.Turtle;
import slogo.model.Variable;

/**
 * DoTimes is the command subclass that runs a code block from 1 to limit times, setting
 * the variable in the body of the command to the loop number every time.
 *
 * @author Livia Seibert
 */
public class DoTimes implements Command {

  private Controller controller;
  private CommandBlock limit;
  private CommandBlock commandBlock;

  public DoTimes(Controller controller, CommandBlock limit, CommandBlock commandBlock) {
    this.controller = controller;
    this.limit = limit;
    this.commandBlock = commandBlock;
  }

  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MathException {
    String[] doTimesPieces = limit.toString().split(" ");
    String varName = doTimesPieces[0];
    String newCommand = "set " + limit.toString();
    Parser parser = new Parser(controller);
    int limitVar = parser.parse(newCommand);
    Variable variable = controller.getVariableHandler().getVariableWithName(varName);
    int parseOutput = 0;
    for (int i = 1; i <= limitVar; i++) {
      controller.getVariableHandler().getVariableWithName(varName).setValue(i);
      parseOutput = parser.parse(commandBlock.toString());
    }
    controller.getVariableHandler().removeVariableWithName(varName);
    return parseOutput;
  }
}
