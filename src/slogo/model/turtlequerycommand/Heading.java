package slogo.model.turtlequerycommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * Heading is the Command subclass that lets the user retrieve the turtle's orientation in degrees.
 *
 * @author Livia Seibert
 */
public class Heading implements Command {

  /**
   * Returns the turtle's orientation.
   *
   * @param turtle turtle whose orientation is being accessed
   * @return turtle's orientation in degrees
   */
  @Override
  public double execute(Turtle turtle) {
    return turtle.getOrientation();
  }
}
