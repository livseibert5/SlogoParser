package slogo.model.movementcommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Left class.
 *
 * @author Rachel Luria
 */
public class LeftTest {

  private Turtle turtle = new Turtle();
  private Left left;

  @Test
  void testTurnLeft90From0() {
    turtle.setOrientation(0);
    left = new Left(new Constant(90));
    assertEquals(90, left.execute(turtle));
    assertEquals(90, turtle.getOrientation());
  }

  @Test
  void testTurnLeft90From90() {
    turtle.setOrientation(90);
    left = new Left(new Constant(90));
    assertEquals(90, left.execute(turtle));
    assertEquals(180, turtle.getOrientation());
  }
}
