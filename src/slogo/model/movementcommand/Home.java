package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * Home is the Command that moves the turtle to location (0, 0).
 *
 * @author Livia Seibert
 */
public class Home implements Command {

  /**
   * Sets turtle's location to (0, 0).
   *
   * @param turtle turtle to move to home location
   * @return distance turtle moved to get home
   */
  @Override
  public double execute(Turtle turtle) {
    double[] oldLocation = turtle.getLocation();
    turtle.setLocation(new double[]{0, 0});
    return Math.sqrt(Math.pow(oldLocation[0] - 0, 2) + Math.pow(oldLocation[1] - 0, 2));
  }
}
