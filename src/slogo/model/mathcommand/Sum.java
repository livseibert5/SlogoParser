package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Sum is the Command that finds the sum of two given inputs.
 *
 * @author Rachel Luria
 */
public class Sum implements Command {

  private final double argument1;
  private final double argument2;

  /**
   * Constructor for the Sum command, takes two constants as its arguments and gets their values by
   * executing a constant command
   *
   * @param argument1 first number being added
   * @param argument2 second number being added
   */
  public Sum(Value argument1, Value argument2) {
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  /**
   * Finds the sum of argument1 and argument2
   *
   * @param turtle turtle object to execute command on
   * @return sum
   */
  @Override
  public double execute(Turtle turtle) {
    return argument1 + argument2;
  }
}