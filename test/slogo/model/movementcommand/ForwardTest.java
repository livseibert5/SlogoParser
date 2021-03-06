package slogo.model.movementcommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.*;

public class ForwardTest {

  private Turtle turtle = new Turtle();
  private Forward forward;

  @BeforeEach
  void setUp() {
    forward = new Forward(new Constant(50));
  }

  @Test
  void testMoveStraightForwardFrom0() {
    turtle.setOrientation(0);
    forward.execute(turtle);
    assertEquals(50, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void testMoveStraightForwardFrom90() {
    turtle.setOrientation(90);
    forward.execute(turtle);
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(50, turtle.getYCoordinate());
  }

  @Test
  void testMoveStraightForwardFrom180() {
    turtle.setOrientation(180);
    forward.execute(turtle);
    assertEquals(-50, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void testMoveStraightForwardFrom270() {
    turtle.setOrientation(270);
    forward.execute(turtle);
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(-50, turtle.getYCoordinate());
  }

  @Test
  void testMoveStraightForwardFrom360() {
    turtle.setOrientation(360);
    forward.execute(turtle);
    assertEquals(50, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void testMoveDiagonallyStraightFrom45() {
    turtle.setOrientation(45);
    forward.execute(turtle);
    assertEquals(35, turtle.getXCoordinate());
    assertEquals(35, turtle.getYCoordinate());
  }
}
