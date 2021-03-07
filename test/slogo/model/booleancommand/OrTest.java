package slogo.model.booleancommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Or test.
 *
 * @author Livia Seibert
 */
public class OrTest {

  private Turtle turtle = new Turtle();
  private Or or;

  @Test
  void testOrWithBothNonzero() {
    or = new Or(new Constant(5), new Constant(6));
    assertEquals(1, or.execute(turtle));
  }

  @Test
  void testOrWithOneNonzero() {
    or = new Or(new Constant(0), new Constant(6));
    assertEquals(1, or.execute(turtle));
  }

  @Test
  void testOrWithTwoZeroes() {
    or = new Or(new Constant(0), new Constant(0));
    assertEquals(0, or.execute(turtle));
  }
}
