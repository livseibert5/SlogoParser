package slogo.model.movementcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Turtle;

/**
 * Tests for Home class.
 *
 * @author Livia Seibert
 */
public class HomeTest {

  private Turtle turtle = new Turtle();
  private Home home = new Home();

  @Test
  void movesHomeFromHome() {
    assertEquals(0, home.execute(turtle));
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void movesHomeFromElseWhere() {
    turtle.setLocation(new double[]{10, 15});
    assertEquals(18, Math.round(home.execute(turtle)));
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }
}
