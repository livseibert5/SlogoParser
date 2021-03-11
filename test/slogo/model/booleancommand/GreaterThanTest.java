package slogo.model.booleancommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for GreaterThan class.
 *
 * @author Livia Seibert
 */
public class GreaterThanTest {

  private GreaterThan greaterThan;
  private Turtle turtle = new Turtle();

  @Test
  void greaterThanWithFirstArgumentGreater() {
    greaterThan = new GreaterThan(new Constant(6), new Constant(5));
    assertEquals(1, greaterThan.execute(turtle));
  }

  @Test
  void greaterThanWithFirstArgumentSmaller() {
    greaterThan = new GreaterThan(new Constant(5), new Constant(6));
    assertEquals(0, greaterThan.execute(turtle));
  }
}
