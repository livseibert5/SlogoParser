package slogo.model.movementcommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for PenDown class.
 *
 * @author Livia Seibert
 */
public class PenDownTest {

  private PenDown penDown;
  private Turtle turtle = new Turtle();

  @BeforeEach
  void setUp() {
    penDown = new PenDown();
  }

  @Test
  void setPenDownWhenPenUp() {
    turtle.setPenUp();
    assertEquals(1, penDown.execute(turtle));
  }

  @Test
  void setPenDownWhenPenDown() {
    turtle.setPenDown();
    assertEquals(1, penDown.execute(turtle));
  }
}
