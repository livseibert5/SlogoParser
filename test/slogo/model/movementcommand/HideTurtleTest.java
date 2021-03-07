package slogo.model.movementcommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Tests for HideTurtle class.
 *
 * @author Livia Seibert
 */
public class HideTurtleTest {

  private HideTurtle hideTurtle;
  private Turtle turtle = new Turtle();

  @BeforeEach
  void setUp() {
    hideTurtle = new HideTurtle();
  }

  @Test
  void hideTurtleWhenTurtleAlreadyHidden() {
    turtle.hideTurtle();
    assertEquals(0, hideTurtle.execute(turtle));
  }

  @Test
  void hideTurtleWhenTurtleVisible() {
    turtle.showTurtle();
    assertEquals(0, hideTurtle.execute(turtle));
  }
}
