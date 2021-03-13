package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Power is the Command subclass that finds the power of two given inputs.
 *
 * @author Rachel Luria
 */
public class Power implements Command{

  private final double base;
  private final double exponent;

  /**
   * Constructor for the Power command, takes two constants as its arguments and
   * gets their values by executing a constant command
   *
   * @param base value that will get raised to a power
   * @param exponent power that the base is raised to
   */
  public Power(Value base, Value exponent){
    this.base = base.getValue();
    this.exponent = exponent.getValue();
  }

  /**
   * Finds the base to the power of the exponent
   *
   * @param turtle turtle object to execute command on
   * @return power
   */
  @Override
  public double execute(Turtle turtle) {
    return Math.pow(base, exponent);
  }
}