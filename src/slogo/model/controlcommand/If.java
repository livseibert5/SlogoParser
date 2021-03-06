package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.CommandBlock;
import slogo.model.Constant;
import slogo.model.backendexceptions.MathException;
import slogo.model.parser.Parser;
import slogo.model.Turtle;

/**
 * If class is the Command that executes a command block if the constant expression passed is
 * non-zero.
 *
 * @author Livia Seibert
 */
public class If implements Command {

  private final double value;
  private final Controller controller;
  private final CommandBlock trueBlock;

  /**
   * Constructor for If command takes in a constant and a block of commands to execute if the
   * constant is non-zero.
   *
   * @param value     constant that determines whether the code block should be run
   * @param trueBlock block to run if the value is non-zero
   */
  public If(Controller controller, Constant value, CommandBlock trueBlock) {
    this.controller = controller;
    this.value = value.getValue();
    this.trueBlock = trueBlock;
  }

  /**
   * Sends the code block to the parser to be executed if the value is non-zero.
   *
   * @param turtle turtle object to execute command on
   * @return result of executing command, 0 otherwise
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
    int parserOutput = 0;
    if (value != 0) {
      Parser parser = new Parser(controller);
      parserOutput = parser.parse(trueBlock.toString());
    }
    return parserOutput;
  }
}
