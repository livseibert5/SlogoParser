package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;
import slogo.model.backendexceptions.MathException;

/**
 * Tests for NaturalLog class.
 *
 * @author Rachel Luria
 */

public class NaturalLogTest {

  private Turtle turtle = new Turtle();
  private NaturalLog log;

  @Test
  void testNatualLogOne() throws MathException {
    log = new NaturalLog(new Constant(1));
    assertEquals(0, log.execute(turtle));
  }

  @Test
  void testLogZero() throws MathException {
    log = new NaturalLog(new Constant(0));
    assertThrows(MathException.class, () -> {
      log.execute(turtle);
    });
  }

  @Test
  void testLogNegative() throws MathException {
    log = new NaturalLog(new Constant(-10));
    assertThrows(MathException.class, () -> {
      log.execute(turtle);
    });
  }
}