package slogo.model.booleancommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * NotEqual class is the Command subclass that returns 1 if the two arguments are
 * not equal to each other.
 *
 * @author Livia Seibert
 */
public class NotEqual implements Command {

  double argument1;
  double argument2;

  /**
   * NotEqual Constructor takes two arguments to compare the values of.
   *
   * @param argument1 first argument to compare
   * @param argument2 second argument to compare
   */
  public NotEqual(Value argument1, Value argument2) {
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  /**
   * Returns 1 if arguments are not equal, 0 otherwise.
   *
   * @param turtle turtle object to execute command on
   * @return 1 if arguments not equal, 0 if arguments equal
   */
  @Override
  public double execute(Turtle turtle) {
    return this.argument1 != this.argument2 ? 1 : 0;
  }
}
