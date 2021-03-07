package slogo.model.booleancommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for NotEqual class.
 *
 * @author Livia Seibert
 */
public class NotEqualTest {

  private NotEqual notEqual;
  private Turtle turtle = new Turtle();

  @Test
  void testNotEqualWithUnequalArguments() {
    notEqual = new NotEqual(new Constant(5), new Constant(6));
    assertEquals(1, notEqual.execute(turtle));
  }

  @Test
  void testNotEqualWithEqualArguments() {
    notEqual = new NotEqual(new Constant(6), new Constant(6));
    assertEquals(0, notEqual.execute(turtle));
  }
}
