package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * HideTurtle is the Command subclass that lets the user remove the turtle icon
 * from the canvas.
 *
 * @author Livia Seibert
 */
public class HideTurtle implements Command {

  /**
   * Hides the turtle from the view.
   *
   * @param turtle turtle object to be hidden
   * @return 0
   */
  @Override
  public double execute(Turtle turtle) {
    turtle.hideTurtle();
    return 0;
  }
}
