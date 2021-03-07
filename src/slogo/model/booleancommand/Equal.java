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

  int argument1;
  int argument2;

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

  @Override
  public int getNumberParameters() {
    return 2;
  }

  @Override
  public double execute(Turtle turtle) {
    return this.argument1 == this.argument2 ? 1 : 0;
  }
}
