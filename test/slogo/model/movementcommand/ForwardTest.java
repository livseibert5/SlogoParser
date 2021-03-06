package slogo.model.movementcommand;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.*;

public class ForwardTest {

  private Turtle turtle = new Turtle();
  private Forward forward;

  @Test
  void testMoveStraightForwardFrom0() {
    turtle.setOrientation(0);
    forward = new Forward(new Constant(50));
    forward.execute(turtle);
    assertEquals(50, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void testMoveStraightForwardFrom90() {
    turtle.setOrientation(Math.PI / 2);
    forward = new Forward(new Constant(50));
    forward.execute(turtle);
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(50, turtle.getYCoordinate());
  }

  @Test
  void testMoveStraightForwardFrom180() {
    turtle.setOrientation(Math.PI);
    forward = new Forward(new Constant(50));
    forward.execute(turtle);
    assertEquals(-50, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void testMoveStraightForwardFrom270() {
    turtle.setOrientation((3/2) * Math.PI);
    forward = new Forward(new Constant(50));
    forward.execute(turtle);
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(-50, turtle.getYCoordinate());
  }
}
