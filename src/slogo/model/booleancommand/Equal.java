package slogo.model.booleancommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Equal class is the Command subclass that returns 1 if the two arguments are
 * equal to each other.
 *
 * @author Livia Seibert
 */
public class Equal implements Command {

  private double argument1;
  private double argument2;

  /**
   * Constructor for Equal command, takes in two constants to determine
   * equality of.
   *
   * @param argument1 first argument to compare
   * @param argument2 second argument to compare
   */
  public Equal(Constant argument1, Constant argument2) {
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  /**
   * Returns 1 if the two arguments are equal, 0 otherwise.
   *
   * @param turtle turtle object to execute command on
   * @return 1 if arguments equal, otherwise 0
   */
  @Override
  public double execute(Turtle turtle) {
    return this.argument1 == this.argument2 ? 1 : 0;
  }
}
