package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Remainder is the Command subclass that finds the remainder of the division of two given inputs.
 *
 * @author Rachel Luria
 */
public class Remainder implements Command {

  private final double argument1;
  private final double argument2;

  /**
   * Constructor for the Remainder command, takes two constants as their arguments and gets their
   * values by executing a constant command
   *
   * @param argument1 being divided in mod equation
   * @param argument2 dividing in mod equation
   */
  public Remainder(Value argument1, Value argument2) {
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  /**
   * Finds the remainder of argument1 divided by argument2
   *
   * @param turtle turtle object to execute command on
   * @return remainder
   */
  @Override
  public double execute(Turtle turtle) {
    return argument1 % argument2;
  }
}