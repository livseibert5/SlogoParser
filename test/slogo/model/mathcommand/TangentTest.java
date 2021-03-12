package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;
import slogo.model.backendexceptions.MathException;

/**
 * Tests for Tangent class.
 *
 * @author Rachel Luria
 */

public class TangentTest {

  private Turtle turtle = new Turtle();
  private Tangent tan;

  @Test
  void testTanZero() throws MathException {
    tan = new Tangent(new Constant(0));
    assertEquals(0, tan.execute(turtle));
  }

  @Test
  void testTan45() throws MathException {
    tan = new Tangent(new Constant(45));
    assertEquals(1, Math.round(tan.execute(turtle)));
  }

  @Test
  void testTan90() {
    tan = new Tangent(new Constant(90));
    assertThrows(MathException.class, () -> {tan.execute(turtle);});
  }

  @Test
  void testTan180() throws MathException {
    tan = new Tangent(new Constant(180));
    assertEquals(0, Math.round(tan.execute(turtle)));
  }

  @Test
  void testTan135() throws MathException {
    tan = new Tangent(new Constant(135));
    assertEquals(-1, Math.round(tan.execute(turtle)));
  }


}