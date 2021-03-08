package slogo.model.turtlequerycommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * YCoordinate is the Command subclass that lets the user access the y-coordinate of the
 * turtle's location.
 *
 * @author Livia Seibert
 */
public class YCoordinate implements Command {

  /**
   * Returns turtle's y-coordinate of its location.
   *
   * @param turtle turtle whose location is being accessed
   * @return y-coordinate of turtle's current location
   */
  @Override
  public double execute(Turtle turtle) {
    return turtle.getYCoordinate();
  }
}
