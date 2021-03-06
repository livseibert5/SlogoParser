package slogo.model.movementcommand;

import slogo.model.Command;

/**
 * Forward class is the Command subclass that determines the end location of
 * the turtle when moved forwards by a certain number of pixels.
 *
 * @author Livia Seibert and Rachel Luria
 */
public class Forward extends MovementCommand implements Command {

  private int pixels;

  /**
   * Constructor for the Forward command, takes a constant as an argument
   * and gets its value by executing the Constant command.
   *
   * @param argument Constant command type containing the number of pixels to move the turtle
   */
  public Forward(Command argument) {
    this.pixels = argument.execute();
  }

  /**
   * Allows access to number of parameters Forward requires so Parser can determine when
   * the proper argument is available to execute the forward command.
   *
   * @return 1, as Forward's only parameter is pixels to be moved
   */
  @Override
  public int getNumberParameters() {
    return 1;
  }

  /**
   * Calculates the end location of the turtle based on its orientation and number of pixels
   * to be moved.
   *
   * @return number of pixels moved
   */
  @Override
  public int execute() {
    int[] newLocation = determineLocation(turtle.getLocation(), turtle.getOrientation(), pixels);
    return pixels;
  }
}
