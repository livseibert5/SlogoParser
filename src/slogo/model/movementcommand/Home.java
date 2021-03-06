package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * Home is the Command subclass that moves the turtle to location (0, 0).
 * 
 * @author Livia Seibert
 */
public class Home implements Command {

  /**
   * Home is always at (0, 0), so no parameter is needed.
   *
   * @return 0, as home location never changes
   */
  @Override
  public int getNumberParameters() {
    return 0;
  }

  /**
   * Sets turtle's location to (0, 0).
   *
   * @param turtle turtle to move to home location
   * @return distance turtle moved to get home
   */
  @Override
  public int execute(Turtle turtle) {
    int[] oldLocation = turtle.getLocation();
    turtle.setLocation(new int[]{0, 0});
    return (int) Math.abs(Math.pow(oldLocation[0] - 0, 2) + Math.pow(oldLocation[1] - 0, 2));
  }
}
