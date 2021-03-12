package slogo.model.booleancommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Not class is the Command subclass that returns 1 if the argument is 0
 * and returns 0 if the argument is non-zero.
 *
 * @author Livia Seibert
 */
public class Not implements Command {

  private double argument;

  /**
   * Not constructor takes in one argument to return the not of.
   *
   * @param argument argument to perform not operation on
   */
  public Not(Value argument) {
    this.argument = argument.getValue();
  }

  /**
   * Returns 1 if the argument is 0, 1 otherwise.
   *
   * @param turtle turtle object to execute command on
   * @return 1 if argument is 0, 1 otherwise
   */
  @Override
  public double execute(Turtle turtle) {
    return this.argument == 0 ? 1 : 0;
  }
}
