package slogo.model.movementcommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for ShowTurtle class.
 *
 * @author Livia Seibert
 */
public class ShowTurtleTest {

  private ShowTurtle showTurtle;
  private Turtle turtle = new Turtle();


  @BeforeEach
  void setUp() {
    showTurtle = new ShowTurtle();
  }

  @Test
  void showTurtleWhenTurtleHidden() {
    turtle.hideTurtle();
    assertEquals(1, showTurtle.execute(turtle));
  }

  @Test
  void showTurtleWhenTurtleVisible() {
    turtle.showTurtle();
    assertEquals(1, showTurtle.execute(turtle));
  }
}
