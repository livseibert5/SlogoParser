package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * SetPosition class is the Command subclass that lets the user specify new x and y coordinates for
 * the turtle.
 *
 * @author Livia Seibert
 */
public class SetPosition implements Command {

  Command argumentX;
  Command argumentY;

  /**
   * Constructor for SetPosition command that takes in two constant commands as arguments to set the
   * new position for the x and y coordinate of the turtle.
   *
   * @param argumentX Constant command with new x coordinate
   * @param argumentY Constant command with new y coordinate
   */
  public SetPosition(Command argumentX, Command argumentY) {
    this.argumentX = argumentX;
    this.argumentY = argumentY;
  }

  /**
   * Allows access to number of parameters SetPosition requires so Parser can determine when the
   * proper argument is available to execute the set position command.
   *
   * @return 2, as SetPosition takes in a new x and y value
   */
  @Override
  public int getNumberParameters() {
    return 2;
  }

  /**
   * Places turtle at new location specified by arguments.
   *
   * @param turtle turtle object to place at new location
   * @return distance turtle moved
   */
  @Override
  public int execute(Turtle turtle) {
    int[] oldLocation = turtle.getLocation();
    turtle.setLocation(new int[]{argumentX.execute(turtle), argumentY.execute(turtle)});
    return (int) Math.sqrt(
        Math.pow(oldLocation[0] - turtle.getXCoordinate(), 2) + Math.pow(oldLocation[1] - turtle
            .getYCoordinate(), 2));
  }
}
