package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Power class.
 *
 * @author Rachel Luria
 */
public class PowerTest {

  private Turtle turtle = new Turtle();
  private Power power;

  @Test
  void testPowerZeroes() {
    power = new Power(new Constant(0), new Constant(0));
    assertEquals(1, power.execute(turtle));
  }

  @Test
  void testPowerOne() {
    power = new Power(new Constant(3), new Constant(1));
    assertEquals(3, power.execute(turtle));
  }

  @Test
  void testPowerFraction() {
    power = new Power(new Constant(4), new Constant(0.5));
    assertEquals(2, power.execute(turtle));
  }
}
