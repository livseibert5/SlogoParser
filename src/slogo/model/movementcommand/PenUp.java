package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * PenUp is the Command subclass that lets the user put the turtle's pen up so that
 * it doesn't leave a trail on the canvas.
 *
 * @author Livia Seibert
 */
public class PenUp implements Command {

  /**
   * Sets the turtle's pen to up.
   *
   * @param turtle turtle whose pen is being put up
   * @return 0
   */
  @Override
  public double execute(Turtle turtle) {
    turtle.setPenUp();
    return 0;
  }
}
