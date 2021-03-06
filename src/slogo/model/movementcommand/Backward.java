package slogo.model.movementcommand;

import slogo.model.Command;

/**
 * Backward class is the Command subclass that determines the end location of
 * the turtle when moved backwards by a certain number of pixels.
 *
 * @author Livia Seibert and Rachel Luria
 */
public class Backward extends MovementCommand implements Command {

  private int pixels;

  /**
   * Constructor for the Backward command, takes a constant as an argument
   * and gets its value by executing the Constant command.
   *
   * @param argument Constant command type containing the number of pixels to move the turtle
   */
  public Backward(Command argument) {
    this.pixels = argument.execute();
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
   * @return number of pixels moved
   */
  @Override
  public int execute() {
    turtle.setOrientation(turtle.getOrientation() + 180);
    int[] newLocation = determineLocation(turtle.getLocation(), turtle.getOrientation(), pixels);
    return pixels;
  }
}
