package slogo.model.turtlequerycommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Turtle;

/**
 * Tests for the Heading class.
 *
 * @author Livia Seibert
 */
public class HeadingTest {

  private Turtle turtle = new Turtle();
  private Heading heading;

  @Test
  void testReturnsCorrectHeading() {
    heading = new Heading();
    turtle.setOrientation(88.5);
    assertEquals(88.5, heading.execute(turtle));
  }
}
