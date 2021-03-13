package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Difference is the Command subclass that finds the difference between two given inputs.
 *
 * @author Rachel Luria
 */
public class Difference implements Command{

  private final double argument1;
  private final double argument2;

  /**
   * Constructor for the Difference command, takes two constants as its arguments and
   * gets their values by executing a constant command
   *
   * @param argument1 argument that gets subtracted from
   * @param argument2 amount that is subtracted
   */
  public Difference(Value argument1, Value argument2){
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  /**
   * Subtracts argument2 from argument1
   *
   * @param turtle turtle object to execute command on
   * @return difference between the arguments
   */
  @Override
  public double execute(Turtle turtle) {
    return argument1 - argument2;
  }
}