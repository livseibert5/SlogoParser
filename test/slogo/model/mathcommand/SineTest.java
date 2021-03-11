package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Sine class.
 *
 * @author Rachel Luria
 */

public class SineTest {
  private Turtle turtle = new Turtle();
  private Sine sin;

  @Test
  void testSinZero() {
    sin = new Sine(new Constant(0));
    assertEquals(0, sin.execute(turtle));
  }

  @Test
  void testSin45() {
    sin = new Sine(new Constant(45));
    assertEquals(0.707, Math.round(sin.execute(turtle) * 1000.0)/1000.0);
  }

  @Test
  void testSin90() {
    sin = new Sine(new Constant(90));
    assertEquals(1, sin.execute(turtle));
  }



}
