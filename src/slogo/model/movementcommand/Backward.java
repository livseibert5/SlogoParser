package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * Backward class is the Command subclass that determines the end location of
 * the turtle when moved backwards by a certain number of pixels.
 *
 * @author Livia Seibert and Rachel Luria
 */
public class Backward extends MovementCommand implements Command {

  private int pixels;
  private Command argument;

  /**
   * Constructor for the Backward command, takes a constant as an argument
   * and gets its value by executing the Constant command.
   *
   * @param argument Constant command type containing the number of pixels to move the turtle
   */
  public Backward(Command argument) {
    this.argument = argument;
  }

  /**
   * Allows access to number of parameters Backward requires so Parser can determine when
   * the proper argument is available to execute the backward command.
   *
   * @return 1, as Backward's only parameter is pixels to be moved
   */
  @Override
  public int getNumberParameters() {
    return 1;
  }

  /**
   * Inverts the turtles orientation and calculates the coordinates of its end location
   * after moving.
   *
   * @param turtle turtle object to execute command on
   * @return number of pixels moved
   */
  @Override
  public int execute(Turtle turtle) {
    this.pixels = argument.execute(turtle);
    turtle.setOrientation(turtle.getOrientation() + 180);
    turtle.setLocation(determineLocation(turtle.getLocation(), Math.toRadians(turtle.getOrientation()), pixels));
    return pixels;
  }
}
