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
 * DoTimes is the Command that runs a code block from 1 to limit times, setting the variable in the
 * body of the command to the loop number every time.
 *
 * @author Livia Seibert
 */
public class DoTimes implements Command {

  private final Controller controller;
  private final CommandBlock limit;
  private final CommandBlock commandBlock;

  /**
   * DoTimes Constructor needs a controller to execute the command block, a limit to determine how
   * many times to run, and a command block to execute.
   *
   * @param controller   controller for the current game
   * @param limit        loop runs 1-limit (inclusive) times
   * @param commandBlock commands to execute each time
   */
  public DoTimes(Controller controller, CommandBlock limit, CommandBlock commandBlock) {
    this.controller = controller;
    this.limit = limit;
    this.commandBlock = commandBlock;
  }

  /**
   * Configures the loop variables and then runs the commandBlock the given number of times,
   * updating the variable on each loop.
   *
   * @param turtle turtle object to execute command on
   * @return result of executing last command
   * @throws ClassNotFoundException    class not found in command factory
   * @throws NoSuchMethodException     constructor doesn't exist in command factory
   * @throws InvocationTargetException issue invoking constructor
   * @throws InstantiationException    issue instantiating command
   * @throws IllegalAccessException    illegal access to command
   * @throws MathException             illegal math command
   */
  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MathException {
    String[] doTimesPieces = limit.toString().split(" ");
    String varName = doTimesPieces[0];
    String newCommand = "set " + limit.toString();
    Parser parser = new Parser(controller);
    int limitVar = parser.parse(newCommand);
    int parseOutput = 0;
    for (int i = 1; i <= limitVar; i++) {
      controller.getVariableHandler().getVariableWithName(varName).setValue(i);
      parseOutput = parser.parse(commandBlock.toString());
    }
    controller.getVariableHandler().removeVariableWithName(varName);
    return parseOutput;
  }
}
