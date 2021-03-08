package slogo.model.movementcommand;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for SetPosition class.
 *
 * @author Livia Seibert
 */
public class SetPositionTest {

  private Turtle turtle = new Turtle();
  private SetPosition setPosition;

  @Test
  void moveToCurrentLocation() {
    setPosition = new SetPosition(new Constant(0), new Constant(0));
    assertEquals(0, setPosition.execute(turtle));
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void moveToNewLocation() {
    setPosition = new SetPosition(new Constant(10), new Constant(15));
    assertEquals(18, (int) setPosition.execute(turtle));
    assertEquals(10, turtle.getXCoordinate());
    assertEquals(15, turtle.getYCoordinate());
  }
}
