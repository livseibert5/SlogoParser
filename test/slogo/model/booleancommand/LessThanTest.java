package slogo.model.booleancommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for LessThan class.
 *
 * @author Livia Seibert
 */
public class LessThanTest {

  private LessThan lessThan;
  private Turtle turtle = new Turtle();

  @Test
  void lessThanWithFirstArgumentGreater() {
    lessThan = new LessThan(new Constant(6), new Constant(5));
    assertEquals(0, lessThan.execute(turtle));
  }

  @Test
  void lessThanWithFirstArgumentSmaller() {
    lessThan = new LessThan(new Constant(5), new Constant(6));
    assertEquals(1, lessThan.execute(turtle));
  }
}
