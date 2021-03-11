package slogo.model.turtlequerycommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Turtle;

/**
 * Tests for the IsPenDown class.
 *
 * @author Livia Seibert
 */
public class IsPenDownTest {

  private IsPenDown isPenDown;
  private Turtle turtle = new Turtle();

  @BeforeEach
  void setUp() {
    isPenDown = new IsPenDown();
  }

  @Test
  void testIsPenDownWhenPenIsDown() {
    turtle.setPenDown();
    assertEquals(1, isPenDown.execute(turtle));
  }

  @Test
  void testIsPenDownWhenPenIsUp() {
    turtle.setPenUp();
    assertEquals(0, isPenDown.execute(turtle));
  }
}
