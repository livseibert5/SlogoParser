package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Minus class.
 *
 * @author Rachel Luria
 */

public class MinusTest {

  private Turtle turtle = new Turtle();
  private Minus minus;

  @Test
  void testMinusZero() {
    minus = new Minus(new Constant(0));
    assertEquals(0, minus.execute(turtle));
    //should throw an error
  }

}