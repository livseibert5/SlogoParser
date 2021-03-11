package slogo.model.turtlequerycommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Turtle;

/**
 * Tests for YCoordinate class.
 *
 * @author Livia Seibert
 */
public class YCoordinateTest {

  private YCoordinate yCoordinate;
  private Turtle turtle = new Turtle();

  @Test
  void yCoordinateReturnsCorrectX() {
    turtle.setLocation(new double[]{12, 13});
    yCoordinate = new YCoordinate();
    assertEquals(13, yCoordinate.execute(turtle));
  }
}
