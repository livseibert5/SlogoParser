package slogo.model.movementcommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Right class.
 *
 * @author Rachel Luria
 */
public class RightTest {

  private Turtle turtle = new Turtle();
  private Right right;

  @Test
  void testTurnRight90From0() {
    turtle.setOrientation(0);
    right = new Right(new Constant(90));
    assertEquals(90, right.execute(turtle));
    assertEquals(270, turtle.getOrientation());
  }

  @Test
  void testTurnRight90From90() {
    turtle.setOrientation(90);
    right = new Right(new Constant(90));
    assertEquals(90, right.execute(turtle));
    assertEquals(0, turtle.getOrientation());
  }

}
