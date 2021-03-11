package slogo.model.booleancommand;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for And class.
 *
 * @author Livia Seibert
 */
public class AndTest {

  private And and;
  private Turtle turtle = new Turtle();

  @Test
  void andTwoNonzeroNumbers() {
    and = new And(new Constant(5), new Constant(6));
    assertEquals(1, and.execute(turtle));
  }

  @Test
  void andZeroAndNonZeroNumbers() {
    and = new And(new Constant(0), new Constant(6));
    assertEquals(0, and.execute(turtle));
  }

  @Test
  void andTwoZeroes() {
    and = new And(new Constant(0), new Constant(0));
    assertEquals(0, and.execute(turtle));
  }
}
