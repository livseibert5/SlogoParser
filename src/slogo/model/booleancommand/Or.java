package slogo.model.booleancommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Or class is the Command subclass that returns 1 if either constant is non-zero and
 * returns 0 otherwise.
 *
 * @author Livia Seibert
 */
public class Or implements Command {

  private double argument1;
  private double argument2;

  /**
   * Constructor for Or command, takes in the 2 arguments.
   *
   * @param argument1 first constant to be compared
   * @param argument2 second constant to be compared
   */
  public Or(Value argument1, Value argument2) {
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  /**
   * Returns 1 if either argument is non-zero, otherwise returns 0.
   *
   * @param turtle turtle object to execute command on
   * @return 1 if either argument non-zero, 0 otherwise
   */
  @Override
  public double execute(Turtle turtle) {
    return this.argument1 != 0 || this.argument2 != 0 ? 1 : 0;
  }
}
