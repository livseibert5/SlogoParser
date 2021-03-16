package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Cosine class.
 *
 * @author Rachel Luria
 */

public class CosineTest {

  private Turtle turtle = new Turtle();
  private Cosine cos;

  @Test
  void testCosineZero() {
    cos = new Cosine(new Constant(0));
    assertEquals(1, cos.execute(turtle));
  }

  @Test
  void testCosine45() {
    cos = new Cosine(new Constant(45));
    assertEquals(0.707, Math.round(cos.execute(turtle) * 1000.0) / 1000.0);
  }

  @Test
  void testCosine90() {
    cos = new Cosine(new Constant(90));
    assertEquals(0, Math.round(cos.execute(turtle)));
  }

  @Test
  void testCosine135() {
    cos = new Cosine(new Constant(135));
    assertEquals(-0.707, Math.round(cos.execute(turtle) * 1000.0) / 1000.0);
  }

  @Test
  void testCosine180() {
    cos = new Cosine(new Constant(180));
    assertEquals(-1, Math.round(cos.execute(turtle)));
  }

  @Test
  void testCosine225() {
    cos = new Cosine(new Constant(225));
    assertEquals(-0.707, Math.round(cos.execute(turtle) * 1000.0) / 1000.0);
  }

  @Test
  void testCosine270() {
    cos = new Cosine(new Constant(270));
    assertEquals(0, Math.round(cos.execute(turtle)));
  }

  @Test
  void testCosine315() {
    cos = new Cosine(new Constant(315));
    assertEquals(0.707, Math.round(cos.execute(turtle) * 1000.0) / 1000.0);
  }

}
