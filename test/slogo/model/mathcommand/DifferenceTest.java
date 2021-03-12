package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Difference class.
 *
 * @author Rachel Luria
 */
public class DifferenceTest {

  private Turtle turtle = new Turtle();
  private Difference difference;

  @Test
  void testDifferenceZeroes() {
    difference = new Difference(new Constant(0), new Constant(0));
    assertEquals(0, difference.execute(turtle));
  }

  @Test
  void testDifferenceSmallNumbers() {
    difference = new Difference(new Constant(4), new Constant(3));
    assertEquals(1, difference.execute(turtle));
  }

  @Test
  void testDifferenceLargeNumbers() {
    difference = new Difference(new Constant(4381), new Constant(1893));
    assertEquals(2488, difference.execute(turtle));
  }

  @Test
  void testDifferenceNegativeNumbers() {
    difference = new Difference(new Constant(-10), new Constant(-33));
    assertEquals(23, difference.execute(turtle));
  }

}


