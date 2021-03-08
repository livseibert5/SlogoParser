package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Remainder class.
 *
 * @author Rachel Luria
 */
public class RemainderTest {

  private Turtle turtle = new Turtle();
  private Remainder remainder;

  @Test
  void testRemainderZero() {
    remainder = new Remainder(new Constant(0), new Constant(10));
    assertEquals(0, remainder.execute(turtle));
  }

  @Test
  void testRemainderNone() {
    remainder = new Remainder(new Constant(20), new Constant(5));
    assertEquals(0, remainder.execute(turtle));
  }

  @Test
  void testRemainderSome() {
    remainder = new Remainder(new Constant(30), new Constant(7));
    assertEquals(2, remainder.execute(turtle));
  }

  @Test
  void testRemainderLargeNumbers() {
    remainder = new Remainder(new Constant(3049438), new Constant(43828));
    assertEquals(25306, remainder.execute(turtle));
  }

  @Test
  void testRemainderTwoNegativeNumbers() {
    remainder = new Remainder(new Constant(-100), new Constant(-6));
    assertEquals(-4, remainder.execute(turtle));
  }
  @Test
  void testQuotientOneNegativeNumber() {
    remainder = new Remainder(new Constant(-100), new Constant(3));
    assertEquals(-1, remainder.execute(turtle));
  }

}


