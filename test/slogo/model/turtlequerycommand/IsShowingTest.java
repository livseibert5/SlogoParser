package slogo.model.turtlequerycommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Turtle;

/**
 * Tests for the IsShowing class.
 *
 * @author Livia Seibert
 */
public class IsShowingTest {

  private IsShowing isShowing;
  private Turtle turtle = new Turtle();

  @BeforeEach
  void setUp() {
    isShowing = new IsShowing();
  }

  @Test
  void testIsShowingWhenTurtleShowing() {
    turtle.showTurtle();
    assertEquals(1, isShowing.execute(turtle));
  }

  @Test
  void testIsShowingWhenTurtleHidden() {
    turtle.hideTurtle();
    assertEquals(0, isShowing.execute(turtle));
  }
}
