package slogo.model.turtlequerycommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * IsPenDown is the Command subclass that lets the user determine whether or not the pen is down so
 * the turtle can draw on the screen.
 *
 * @author Livia Seibert
 */
public class IsPenDown implements Command {

  /**
   * Returns 1 if the turtle's pen is down, 0 otherwise.
   *
   * @param turtle turtle whose pen status is being accessed
   * @return 1 if turtle's pen is down, 0 otherwise
   */
  @Override
  public double execute(Turtle turtle) {
    return turtle.penIsDown() ? 1 : 0;
  }
}
