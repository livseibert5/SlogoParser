package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Turtle;

/**
 * Tests for Pi Class
 *
 * @author Rachel Luria
 */
public class PiTest {

  private Turtle turtle = new Turtle();
  private Pi pi;


  @Test
  void testPi() {
    pi = new Pi();
    assertEquals(3.14, Math.round(pi.execute(turtle) * 100.0) / 100.0);
  }

}
