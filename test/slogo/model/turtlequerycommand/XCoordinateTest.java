package slogo.model.turtlequerycommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Turtle;

/**
 * Tests for XCoordinate class.
 *
 * @author Livia Seibert
 */
public class XCoordinateTest {

  private XCoordinate xCoordinate;
  private Turtle turtle = new Turtle();

  @Test
  void xCoordinateReturnsCorrectX() {
    turtle.setLocation(new double[]{12, 13});
    xCoordinate = new XCoordinate();
    assertEquals(12, xCoordinate.execute(turtle));
  }
}
