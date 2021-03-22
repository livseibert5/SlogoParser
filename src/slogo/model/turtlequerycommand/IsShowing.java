package slogo.model.turtlequerycommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * IsShowing is the Command that lets the user determine whether or not the turtle is
 * displayed on the screen.
 *
 * @author Livia Seibert
 */
public class IsShowing implements Command {

  /**
   * Return 1 if the turtle is showing on the canvas, 0 otherwise.
   *
   * @param turtle turtle whose display status we're accessing
   * @return 1 if turtle is showing, 0 if turtle is hidden
   */
  @Override
  public double execute(Turtle turtle) {
    return turtle.isShowing() ? 1 : 0;
  }
}
