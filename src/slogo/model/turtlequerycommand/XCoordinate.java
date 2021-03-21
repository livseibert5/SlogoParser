package slogo.model.turtlequerycommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * XCoordinate is the Command that lets the user access the x-coordinate of the turtle's
 * location.
 *
 * @author Livia Seibert
 */
public class XCoordinate implements Command {

  /**
   * Returns turtle's x-coordinate of its location.
   *
   * @param turtle turtle whose location is being accessed
   * @return x-coordinate of turtle's current location
   */
  @Override
  public double execute(Turtle turtle) {
    return turtle.getXCoordinate();
  }
}
