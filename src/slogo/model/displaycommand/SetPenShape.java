package slogo.model.displaycommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * SetPenShape is the command subclass that lets the user choose the shape of the turtle
 * by setting its index.
 *
 * @author Rachel Luria
 */
public class SetPenShape implements Command {

  private double value;

  /**
   * SetPenShape constructor takes a value that corresponds to the index of the new
   * pen shape.
   *
   * @param value
   */
  public SetPenShape(Value value) {
    this.value = value.getValue();
  }

  /**
   * Sets the turtle's pen shape to the shape specified by the constructor parameter.
   *
   * @param turtle turtle object to execute command on
   * @return index of new pen shape
   */
  @Override
  public double execute(Turtle turtle) {
    turtle.setPenShape(value);
    return value;
  }

}
