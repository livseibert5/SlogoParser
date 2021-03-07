package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;
import slogo.model.movementcommand.Left;

/**
 * Tests for Backward class.
 *
 * @author Livia Seibert
 */
public class SumTest {

  private Turtle turtle = new Turtle();
  private Sum sum;

  @Test
  void testSumZeroes() {
    sum = new Sum(new Constant(0), new Constant(0));
    assertEquals(0, sum.execute(turtle));
  }

}


