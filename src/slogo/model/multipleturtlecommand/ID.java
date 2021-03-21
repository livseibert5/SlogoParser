package slogo.model.multipleturtlecommand;

import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.Turtle;

/**
 * ID is the Command that returns the ID of the current active turtle.
 *
 * @author Livia Seibert
 */
public class ID implements Command {

  private final Controller controller;

  /**
   * ID constructor requires the controller to access the turtle handler to retrieve the turtle id.
   *
   * @param controller controller for current game
   */
  public ID(Controller controller) {
    this.controller = controller;
  }

  /**
   * Returns id of current turtle.
   *
   * @param turtle turtle object to execute command on
   * @return id of turtle
   */
  @Override
  public double execute(Turtle turtle) {
    return controller.getTurtleHandler().getTurtleId(turtle);
  }
}
