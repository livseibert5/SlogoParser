package slogo.model.multipleturtlecommand;

import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.Turtle;

/**
 * ID is the Command subclass that returns the ID of the current active turtle.
 *
 * @author Livia Seibert
 */
public class ID implements Command {

  private final Controller controller;

  public ID(Controller controller) {
    this.controller = controller;
  }

  @Override
  public double execute(Turtle turtle) {
    return controller.getTurtleHandler().getTurtleId(turtle);
  }
}
