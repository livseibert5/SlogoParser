package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Backward class is the Command that determines the end location of the turtle when moved backwards
 * by a certain number of pixels.
 *
 * @author Livia Seibert and Rachel Luria
 */
public class Backward extends MovementCommand implements Command {

  private final double pixels;

  /**
   * Constructor for the Backward command, takes a constant as an argument and gets its value by
   * executing the Constant command.
   *
   * @param pixels Constant that contains the number of pixels to move
   */
  public Backward(Value pixels) {
    this.pixels = pixels.getValue();
  }

  /**
   * Inverts the turtles orientation and calculates the coordinates of its end location after
   * moving.
   *
   * @param turtle turtle object to execute command on
   * @return number of pixels moved
   */
  @Override
  public double execute(Turtle turtle) {
    turtle.setOrientation(turtle.getOrientation() + 180);
    turtle.setLocation(
        determineLocation(turtle.getLocation(), Math.toRadians(turtle.getOrientation()), pixels));
    return pixels;
  }
}
