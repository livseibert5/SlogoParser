package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * SetHeading is the Command subclass that lets the user set a new orientation for the Turtle
 * object.
 *
 * @author Livia Seibert and Rachel Luria
 */
public class SetHeading implements Command {

  private final double orientation;

  /**
   * Constructor for the SetHeading command, takes a constant as an argument and later gets its
   * value for the new orientation by executing the argument.
   *
   * @param orientation Constant containing value for new orientation
   */
  public SetHeading(Value orientation) {
    this.orientation = orientation.getValue();
  }

  /**
   * Set's turtle's orientation to be the new orientation specified by the argument.
   *
   * @param turtle turtle object to change orientation of
   * @return new orientation
   */
  @Override
  public double execute(Turtle turtle) {
    turtle.setOrientation(orientation);
    return (int) orientation;
  }
}
