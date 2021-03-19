package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;
import slogo.model.backendexceptions.MathException;

/**
 * NaturalLog is the Command subclass that finds the naturallog of a given input.
 *
 * @author Rachel Luria
 */
public class NaturalLog implements Command {

  private final double argument;

  /**
   * Constructor for the NaturalLog command, takes a constant as its argument and gets its value by
   * executing a constant command
   *
   * @param argument Constant that the naturallog gets taken of
   */
  public NaturalLog(Value argument) {
    this.argument = argument.getValue();
  }

  /**
   * Finds the natural log of the double argument in degrees
   *
   * @param turtle turtle object to execute command on
   * @return natural log of the argument
   * @throws MathException invalid input causes a math error
   */
  @Override
  public double execute(Turtle turtle) throws MathException {
    if (argument > 0) {
      return Math.log(argument);
    } else {
      String ERROR_MESSAGE = "Invalid Log Input";
      throw new MathException(ERROR_MESSAGE);
    }
  }
}