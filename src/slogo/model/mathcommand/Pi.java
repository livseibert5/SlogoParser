package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * Pi is the Command that finds the value of Pi.
 *
 * @author Rachel Luria
 */
public class Pi implements Command {

  /**
   * Finds the value of pi
   *
   * @param turtle turtle object to execute command on
   * @return pi
   */
  @Override
  public double execute(Turtle turtle) {
    return Math.PI;
  }
}