package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;
import slogo.model.backendexceptions.MathException;

/**
 * Tangent is the Command subclass that finds the tangent of a given input.
 *
 * @author Rachel Luria
 */
public class Tangent implements Command {

  private final double argument;
  private final String ERROR_MESSAGE = "Invalid Tangent Input";

  /**
   * Constructor for the Tangent command, takes a constant as its argument and gets its value by
   * executing a constant command
   *
   * @param argument Constant that the tangent gets taken of
   */
  public Tangent(Value argument) {
    this.argument = argument.getValue();
  }

  /**
   * Finds the tangent of the given argument in degrees
   *
   * @param turtle turtle object to execute command on
   * @return tangent
   * @throws MathException invalid input causes a math error
   */
  @Override
  public double execute(Turtle turtle) throws MathException {
    if (argument % 90 == 0 && argument % 180 != 0) {
      throw new MathException(ERROR_MESSAGE);
    } else {
      return Math.tan(Math.toRadians(argument));
    }
  }
}