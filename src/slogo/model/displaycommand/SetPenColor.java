package slogo.model.displaycommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * SetPenColor is the Command that lets the user choose the color of the turtle's pen
 * by setting its index.
 *
 * @author Livia Seibert
 */
public class SetPenColor implements Command {

  private final double value;

  /**
   * SetPenColor constructor takes a value that corresponds to the index of the new
   * pen color.
   *
   * @param value index of new pen color
   */
  public SetPenColor(Value value) {
    this.value = value.getValue();
  }

  /**
   * Sets the turtle's pen color to the color specified by the constructor parameter.
   *
   * @param turtle turtle object to execute command on
   * @return index of new pen color
   */
  @Override
  public double execute(Turtle turtle) {
    turtle.setPenColor(value);
    return value;
  }
}
