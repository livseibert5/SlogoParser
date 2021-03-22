package slogo.model.booleancommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * LessThan class is the Command that returns 1 if the first argument is less than the
 * second argument, and returns 0 otherwise.
 *
 * @author Livia Seibert
 */
public class LessThan implements Command {

  private final double argument1;
  private final double argument2;

  /**
   * Constructor for LessThan command, takes in 2 arguments to compare magnitude of.
   *
   * @param argument1 first argument to compare
   * @param argument2 second argument to compare
   */
  public LessThan(Value argument1, Value argument2) {
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  /**
   * Return 1 if the first argument is less than the second, 0 otherwise.
   *
   * @param turtle turtle object to execute command on
   * @return 1 if argument1 is less than argument2, 0 otherwise
   */
  @Override
  public double execute(Turtle turtle) {
    return this.argument1 < this.argument2 ? 1 : 0;
  }
}
