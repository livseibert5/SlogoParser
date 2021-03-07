package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Quotient class.
 *
 * @author Rachel Luria
 */
public class QuotientTest {

  private Turtle turtle = new Turtle();
  private Quotient quotient;

  @Test
  void testQuotientZero() {
    quotient = new Quotient(new Constant(0), new Constant(10));
    assertEquals(0, quotient.execute(turtle));
  }

  @Test
  void testQuotientSmallNumbers() {
    quotient = new Quotient(new Constant(20), new Constant(5));
    assertEquals(4, quotient.execute(turtle));
  }

  @Test
  void testQuotientargeNumbers() {
    quotient= new Quotient(new Constant(10500), new Constant(350));
    assertEquals(30, quotient.execute(turtle));
  }

  @Test
  void testQuotientTwoNegativeNumbers() {
    quotient = new Quotient(new Constant(-100), new Constant(-10));
    assertEquals(10, quotient.execute(turtle));
  }
  @Test
  void testQuotientOneNegativeNumber() {
    quotient = new Quotient(new Constant(-100), new Constant(10));
    assertEquals(-10, quotient.execute(turtle));
  }

}


