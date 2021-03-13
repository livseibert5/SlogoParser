package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for ArcTangent class.
 *
 * @author Rachel Luria
 */
public class ArcTangentTest {

  private Turtle turtle = new Turtle();
  private ArcTangent arctan;

  @Test
  void testArcTanZero() {
    arctan = new ArcTangent(new Constant(0));
    assertEquals(0, arctan.execute(turtle));
  }


  @Test
  void testArcTan45() {
    arctan = new ArcTangent(new Constant(45));
    assertEquals(88.727, Math.round(arctan.execute(turtle) * 1000.0) / 1000.0);
  }
  /*

  @Test
  void testArcTan90() {
    arctan = new ArcTangent(new Constant(90));
    assertEquals(89.363, Math.round(arctan.execute(turtle) * 1000.0) / 1000.0);
  }

  @Test
  void testArcTanNeg45() {
    arctan = new ArcTangent(new Constant(-45));
    assertEquals(-88.727, Math.round(arctan.execute(turtle) * 1000.0) / 1000.0);
  }

  @Test
  void testArcTan135() {
    arctan = new ArcTangent(new Constant(135));
    assertEquals(89.576, Math.round(arctan.execute(turtle) * 1000.0) / 1000.0);
  }

  @Test
  void testArcTan180() {
    arctan = new ArcTangent(new Constant(180));
    assertEquals(89.682, Math.round(arctan.execute(turtle) * 1000.0) / 1000.0);
  }

  @Test
  void testArcTan225() {
    arctan = new ArcTangent(new Constant(225));
    assertEquals(89.745, Math.round(arctan.execute(turtle) * 1000.0) / 1000.0);
  }

   */
}


