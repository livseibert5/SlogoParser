package slogo.model.movementcommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for PenUp class.
 *
 * @author Livia Seibert
 */
public class PenUpTest {

  private PenUp penUp;
  private Turtle turtle = new Turtle();

  @BeforeEach
  void setUp() {
    penUp = new PenUp();
  }

  @Test
  void setPenUpWhenPenUp() {
    turtle.setPenUp();
    assertEquals(0, penUp.execute(turtle));
  }

  @Test
  void setPenUpWhenPenDown() {
    turtle.setPenDown();
    assertEquals(0, penUp.execute(turtle));
  }
}
