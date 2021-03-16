package slogo.model.displaycommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * SetPenSize is the Command subclass that lets the user set the thickness of the turtle's pen
 * in pixels.
 *
 * @author Livia Seibert
 */
public class SetPenSize implements Command {

  private double value;

  /**
   * SetPenSize constructor takes a value that corresponds to the pixel size for the pen's
   * new width.
   *
   * @param value new thickness of pen in pixels
   */
  public SetPenSize(Value value) {
    this.value = value.getValue();
  }

  /**
   * Sets turtle's pen thickness to the newly specified value and returns the pixel size.
   *
   * @param turtle turtle object to execute command on
   * @return new thickness of turtle's pen in pixels
   */
  @Override
  public double execute(Turtle turtle) {
    turtle.setPenThickness(value);
    return value;
  }
}
