package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Quotient is the Command subclass that finds the quotient of two given inputs.
 *
 * @author Rachel Luria
 */
public class Quotient implements Command{

  private final double argument1;
  private final double argument2;

  /**
   * Constructor for the Quotient command, takes two constants as its arguments and
   * gets their values by executing a constant command
   *
   * @param argument1 number being divided
   * @param argument2 amount argument1 is being divided by
   */
  public Quotient(Value argument1, Value argument2){
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  /**
   * Finds the value of argument1 divided by argument2
   *
   * @param turtle turtle object to execute command on
   * @return quotient
   */
  @Override
  public double execute(Turtle turtle) {
    return argument1 / argument2;
  }
}