package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * ArcTangent is the Command that finds the arctangent of a given input.
 *
 * @author Rachel Luria
 */
public class ArcTangent implements Command {

  private final double argument;

  /**
   * Constructor for the ArcTangent command, takes a constant as its argument and gets its value by
   * executing a constant command
   *
   * @param argument Constant that the arctangent gets taken of
   */
  public ArcTangent(Value argument) {
    this.argument = argument.getValue();
  }

  /**
   * Finds the arctangent of the double argument in degrees
   *
   * @param turtle turtle object to execute command on
   * @return arctangent of the argument
   */
  @Override
  public double execute(Turtle turtle) {
    return Math.toDegrees(Math.atan(argument));
  }
}