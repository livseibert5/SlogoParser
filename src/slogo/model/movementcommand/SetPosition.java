package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * SetPosition class is the Command subclass that lets the user specify new x and y coordinates for
 * the turtle.
 *
 * @author Livia Seibert
 */
public class SetPosition implements Command {

  double argumentX;
  double argumentY;

  /**
   * Constructor for SetPosition command that takes in two constant commands as arguments to set the
   * new position for the x and y coordinate of the turtle.
   *
   * @param argumentX Constant with new x coordinate
   * @param argumentY Constant with new y coordinate
   */
  public SetPosition(Value argumentX, Value argumentY) {
    this.argumentX = argumentX.getValue();
    this.argumentY = argumentY.getValue();
  }

  /**
   * Places turtle at new location specified by arguments.
   *
   * @param turtle turtle object to place at new location
   * @return distance turtle moved
   */
  @Override
  public double execute(Turtle turtle) {
    double[] oldLocation = turtle.getLocation();
    turtle.setLocation(new double[]{argumentX, argumentY});
    return Math.sqrt(
        Math.pow(oldLocation[0] - turtle.getXCoordinate(), 2) + Math.pow(oldLocation[1] - turtle
            .getYCoordinate(), 2));
  }
}
