package slogo.model.movementcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Backward class.
 *
 * @author Livia Seibert
 */
public class BackwardTest {

  private Turtle turtle = new Turtle();
  private Backward backward;

  @BeforeEach
  void setUp() {
    backward = new Backward(new Constant(50));
  }

  @Test
  void testMoveStraightBackwardFrom0() {
    turtle.setOrientation(0);
    backward.execute(turtle);
    assertEquals(180, turtle.getOrientation());
    assertEquals(-50, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void testMoveNotFromOrigin() {
    turtle.setOrientation(45);
    turtle.setLocation(new double[]{3, 5});
    backward = new Backward(new Constant(16));
    assertEquals(16, backward.execute(turtle));
    assertEquals(225, turtle.getOrientation());
    assertEquals(-8.31, turtle.getXCoordinate());
    assertEquals(-6.31, turtle.getYCoordinate());
  }

  @Test
  void testMoveStraightBackwardFrom90() {
    turtle.setOrientation(90);
    backward.execute(turtle);
    assertEquals(270, turtle.getOrientation());
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(-50, turtle.getYCoordinate());
  }

  @Test
  void testMoveStraightBackwardFrom180() {
    turtle.setOrientation(180);
    backward.execute(turtle);
    assertEquals(360, turtle.getOrientation());
    assertEquals(50, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void testMoveStraightBackwardFrom270() {
    turtle.setOrientation(270);
    backward.execute(turtle);
    assertEquals(90, turtle.getOrientation());
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(50, turtle.getYCoordinate());
  }

  @Test
  void testMoveStraightBackwardFrom360() {
    turtle.setOrientation(360);
    backward.execute(turtle);
    assertEquals(180, turtle.getOrientation());
    assertEquals(-50, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void testMoveDiagonallyBackwardFrom45() {
    turtle.setOrientation(45);
    backward.execute(turtle);
    assertEquals(225, turtle.getOrientation());
    assertEquals(-35, Math.round(turtle.getXCoordinate()));
    assertEquals(-35, Math.round(turtle.getYCoordinate()));
  }

  @Test
  void testMoveDiagonallyBackwardFrom135() {
    turtle.setOrientation(135);
    backward.execute(turtle);
    assertEquals(315, turtle.getOrientation());
    assertEquals(35, Math.round(turtle.getXCoordinate()));
    assertEquals(-35, Math.round(turtle.getYCoordinate()));
  }

  @Test
  void testMoveDiagonallyBackwardFrom225() {
    turtle.setOrientation(225);
    backward.execute(turtle);
    assertEquals(45, turtle.getOrientation());
    assertEquals(35, Math.round(turtle.getXCoordinate()));
    assertEquals(35, Math.round(turtle.getYCoordinate()));
  }

  @Test
  void testMoveDiagonallyBackwardFrom315() {
    turtle.setOrientation(315);
    backward.execute(turtle);
    assertEquals(135, turtle.getOrientation());
    assertEquals(-35, Math.round(turtle.getXCoordinate()));
    assertEquals(35, Math.round(turtle.getYCoordinate()));
  }
}
