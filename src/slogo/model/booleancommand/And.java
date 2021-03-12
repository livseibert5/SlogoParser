package slogo.model.booleancommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * And class is the Command subclass that returns 1 if both constants are non-zero and
 * 0 otherwise.
 *
 * @author Livia Seibert
 */
public class And implements Command {

  double argument1;
  double argument2;

  /**
   * Constructor for And argument, takes in two constants to compare.
   *
   * @param argument1 first constant to compare
   * @param argument2 second constant to compare
   */
  public And(Value argument1, Value argument2) {
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  /**
   * Returns 1 if both arguments are non-zero and returns 0 otherwise.
   *
   * @param turtle current turtle object
   * @return 1 if both arguments non-zero, else 0
   */
  @Override
  public double execute(Turtle turtle) {
    return this.argument1 != 0 && this.argument2 != 0 ? 1 : 0;
  }
}
