package slogo.model.multipleturtlecommand;

import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.Turtle;

/**
 * Turtles is the Command subclass that returns the number of turtles created so far.
 *
 * @author Livia Seibert
 */
public class Turtles implements Command {

  private final Controller controller;

  /**
   * Constructor for Turtles needs the controller to determine how many turtles are in the TurtleHandler.
   *
   * @param controller current controller for game
   */
  public Turtles(Controller controller) {
    this.controller = controller;
  }

  /**
   * Returns number of turtles created so far.
   *
   * @param turtle turtle object to execute command on
   * @return number of turtles created
   */
  @Override
  public double execute(Turtle turtle) {
    return controller.getTurtleHandler().getNumberTurtles();
  }
}
