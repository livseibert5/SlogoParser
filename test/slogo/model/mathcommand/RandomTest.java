package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Random class.
 *
 * @author Rachel Luria
 */
public class RandomTest {
  private Turtle turtle = new Turtle();
  private Random random;

  @Test
  void testPowerZeroes() {
    random = new Random(new Constant(5));
    assertTrue(random.execute(turtle) > 0 && random.execute(turtle) < 5);
  }

}
