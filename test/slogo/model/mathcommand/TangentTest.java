package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Tangent class.
 *
 * @author Rachel Luria
 */

public class TangentTest {

  private Turtle turtle = new Turtle();
  private Tangent tan;

  @Test
  void testTanZero() {
    tan = new Tangent(new Constant(0));
    assertEquals(0, tan.execute(turtle));
  }

  @Test
  void testTan45() {
    tan = new Tangent(new Constant(45));
    assertEquals(1, Math.round(tan.execute(turtle)));
  }

  @Test
  void testTan90() {
    tan = new Tangent(new Constant(90));
    //should throw an error
  }


}