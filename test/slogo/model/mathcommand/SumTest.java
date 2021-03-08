package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Sum class.
 *
 * @author Rachel Luria
 */
public class SumTest {

  private Turtle turtle = new Turtle();
  private Sum sum;

  @Test
  void testSumZeroes() {
    sum = new Sum(new Constant(0), new Constant(0));
    assertEquals(0, sum.execute(turtle));
  }

  @Test
  void testSumSmallNumbers() {
    sum = new Sum(new Constant(3), new Constant(4));
    assertEquals(7, sum.execute(turtle));
  }

  @Test
  void testSumLargeNumbers() {
    sum = new Sum(new Constant(1893), new Constant(4381));
    assertEquals(6274, sum.execute(turtle));
  }

  @Test
  void testSumNegativeNumbers() {
    sum = new Sum(new Constant(-10), new Constant(-33));
    assertEquals(-43, sum.execute(turtle));
  }

}


