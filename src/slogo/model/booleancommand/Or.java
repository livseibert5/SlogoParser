package slogo.model.booleancommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Or class is the Command subclass that returns 1 if either constant is non-zero and
 * returns 0 otherwise.
 *
 * @author Livia Seibert
 */
public class Or implements Command {

  int argument1;
  int argument2;

  /**
   * Constructor for Or command, takes in the 2 arguments.
   *
   * @param argument1 first constant to be compared
   * @param argument2 second constant to be compared
   */
  public Or(Constant argument1, Constant argument2) {
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    return this.argument1 != 0 || this.argument2 != 0 ? 1 : 0;
  }
}
