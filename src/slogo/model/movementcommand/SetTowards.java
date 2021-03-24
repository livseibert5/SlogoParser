package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * SetTowards is the Command that lets the user specify a coordinate that it wants the turtle to
 * face, and then sets the turtle's orientation to face this location.
 *
 * @author Rachel Luria
 */
public class SetTowards implements Command {

  private final double x;
  private final double y;
  private final int ZERO_DEGREES = 0;
  private final int NINETY_DEGREES = 90;
  private final int ONEEIGHTY_DEGREES = 180;
  private final int TWOSEVENTY_DEGREES = 270;
  private final int THREESIXTY_DEGREES = 360;

  public SetTowards(Value argument1, Value argument2) {
    this.x = argument1.getValue();
    this.y = argument2.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    double xdiff = x - turtle.getXCoordinate();
    double ydiff = y - turtle.getYCoordinate();
    int degree;
    if (xdiff == ZERO_DEGREES) {
      if (ydiff > ZERO_DEGREES) {
        degree = NINETY_DEGREES;
      } else {
        degree = TWOSEVENTY_DEGREES;
      }
    } else {
      degree = (int) Math.toDegrees((Math.atan(ydiff / xdiff)));
      if (xdiff < ZERO_DEGREES) {
        degree += ONEEIGHTY_DEGREES;
      } else if (ydiff < ZERO_DEGREES) {
        degree += THREESIXTY_DEGREES;
      }
    }
    turtle.setOrientation(degree);
    return degree;
  }
}
