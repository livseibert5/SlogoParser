package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * PenDown is the Command subclass that lets the user set the turtle's pen down so that
 * it can draw on the canvas.
 *
 * @author Livia Seibert
 */
public class PenDown implements Command {

  /**
   * Sets the turtle's pen to down.
   *
   * @param turtle turtle whose pen is set down
   * @return 1
   */
  @Override
  public double execute(Turtle turtle) {
    turtle.setPenDown();
    return 1;
  }
}
