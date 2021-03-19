package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Forward class is the Command subclass that determines the end location of the turtle when moved
 * forwards by a certain number of pixels.
 *
 * @author Livia Seibert and Rachel Luria
 */
public class Forward extends MovementCommand implements Command {

  private final double pixels;

  /**
   * Constructor for the Forward command, takes a constant as an argument and gets its value by
   * executing the Constant command.
   *
   * @param pixels Constant containing the number of pixels to move the turtle
   */
  public Forward(Value pixels) {
    this.pixels = pixels.getValue();
  }

  /**
   * Calculates the end location of the turtle based on its orientation and number of pixels to be
   * moved.
   *
   * @param turtle turtle object to execute command on
   * @return number of pixels moved
   */
  @Override
  public double execute(Turtle turtle) {
    turtle.setLocation(
        determineLocation(turtle.getLocation(), Math.toRadians(turtle.getOrientation()), pixels));
    return pixels;
  }
}
