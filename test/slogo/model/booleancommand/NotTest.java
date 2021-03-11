package slogo.model.booleancommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Not class.
 *
 * @author Livia Seibert
 */
public class NotTest {

  private Turtle turtle = new Turtle();
  private Not not;

  @Test
  void testNotWithNonzero() {
    not = new Not(new Constant(5));
    assertEquals(0, not.execute(turtle));
  }

  @Test
  void testNotWithZero() {
    not = new Not(new Constant(0));
    assertEquals(1, not.execute(turtle));
  }
}
