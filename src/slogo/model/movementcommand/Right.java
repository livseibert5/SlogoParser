package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Right class is the Command subclass that determines the end location of the turtle when moved
 * right by a certain number of pixels.
 *
 * @author Rachel Luria and Livia Seibert
 */
public class Right extends MovementCommand implements Command {

  private double degrees;

  /**
   * Constructor for the Right command, takes a constant as an argument and gets its value by
   * executing the Constant command.
   *
   * @param degrees Constant or Variable containing the number of pixels to move the turtle
   */
  public Right(Value degrees) {
    this.degrees = degrees.getValue();
  }

  /**
   * Turns turtle to the right, then calculates the end location of the turtle based on the number
   * of pixels passed as an argument.
   *
   * @param turtle turtle object to execute command on
   * @return number of pixels moved
   */
  public double execute(Turtle turtle) {
    turtle.setOrientation(turtle.getOrientation() - degrees);
    return degrees;
  }
}
