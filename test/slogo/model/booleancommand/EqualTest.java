package slogo.model.booleancommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Equal class.
 *
 * @author Livia Seibert
 */
public class EqualTest {

  private Equal equal;
  private Turtle turtle = new Turtle();

  @Test
  void equalWhenArgumentsAreEqual() {
    equal = new Equal(new Constant(5), new Constant(5));
    assertEquals(1, equal.execute(turtle));
  }

  @Test
  void equalWhenArgumentsAreUnequal() {
    equal = new Equal(new Constant(6), new Constant(5));
    assertEquals(0, equal.execute(turtle));
  }
}
