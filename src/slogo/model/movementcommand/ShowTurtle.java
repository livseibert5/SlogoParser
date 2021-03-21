package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * ShowTurtle is the Command that lets the user make the turtle visible on the canvas.
 *
 * @author Livia Seibert
 */
public class ShowTurtle implements Command {

  /**
   * Shows the turtle in the view.
   *
   * @param turtle turtle object to show
   * @return 1
   */
  @Override
  public double execute(Turtle turtle) {
    turtle.showTurtle();
    return 1;
  }
}
