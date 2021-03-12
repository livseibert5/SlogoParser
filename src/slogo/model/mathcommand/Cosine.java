package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Cosine is the Command subclass that finds the cosine of a given input.
 *
 * @author Rachel Luria
 */
public class Cosine implements Command{

  private final double argument;

  /**
   * Constructor for the Cosine command, takes a constant as its argument and
   * gets its value by executing a constant command
   *
   * @param argument Constant that the cosine gets taken of
   */
  public Cosine(Value argument){
    this.argument = argument.getValue();
  }

  /**
   * Finds the cosine of the double argument in degrees
   *
   * @param turtle turtle object to execute command on
   * @return cosine of the argument
   */
  @Override
  public double execute(Turtle turtle) {
    return Math.cos(Math.toRadians(argument));
  }
}