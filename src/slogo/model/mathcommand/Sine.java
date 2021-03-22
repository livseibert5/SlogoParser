package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Sine is the Command that finds the sine of a given input.
 *
 * @author Rachel Luria
 */
public class Sine implements Command {

  private final double argument;

  /**
   * Constructor for the Sine command, takes a constant as its argument and gets its value by
   * executing a constant command
   *
   * @param argument Constant that the sine gets taken of
   */
  public Sine(Value argument) {
    this.argument = argument.getValue();
  }

  /**
   * Finds the sine of the double argument in degrees
   *
   * @param turtle turtle object to execute command on
   * @return sine of the argument
   */
  @Override
  public double execute(Turtle turtle) {
    return Math.sin(Math.toRadians(argument));
  }
}