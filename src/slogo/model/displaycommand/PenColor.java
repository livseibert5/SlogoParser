package slogo.model.displaycommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * PenColor is the Command that returns the index of the current color of the turtle's pen.
 *
 * @author Livia Seibert
 */
public class PenColor implements Command {

  /**
   * Returns index of current color of turtle's pen.
   *
   * @param turtle turtle object to execute command on
   * @return index of current color of turtle's pen
   */
  @Override
  public double execute(Turtle turtle) {
    return turtle.getPenColor();
  }
}
