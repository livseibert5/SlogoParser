package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Random is the Command subclass that finds a random number beneath a certain threshold.
 *
 * @author Rachel Luria
 */
public class Random implements Command {

  private final double argument;

  /**
   * Constructor for the Random command, takes a constant as its argument and gets its value by
   * executing a constant command
   *
   * @param argument threshold for Random command
   */
  public Random(Value argument) {
    this.argument = argument.getValue();
  }

  /**
   * Finds the a random number between 0 and the argument
   *
   * @param turtle turtle object to execute command on
   * @return random number
   */
  @Override
  public double execute(Turtle turtle) {
    return Math.random() * argument;
  }
}