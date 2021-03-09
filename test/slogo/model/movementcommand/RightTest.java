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

  @BeforeEach
  void setUp() {
    right = new Right(new Constant(50));
  }

  @Test
  void testMoveRightFrom0() {
    turtle.setOrientation(0);
    right.execute(turtle);
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(-50, turtle.getYCoordinate());
  }

  @Test
  void testMoveRightFrom90() {
    turtle.setOrientation(90);
    right.execute(turtle);
    assertEquals(50, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void testMoveRightFrom180() {
    turtle.setOrientation(180);
    right.execute(turtle);
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(50, turtle.getYCoordinate());
  }

  @Test
  void testMoveRightFrom270() {
    turtle.setOrientation(270);
    right.execute(turtle);
    assertEquals(-50, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void testMoveRightFrom45() {
    turtle.setOrientation(45);
    right.execute(turtle);
    assertEquals(35, Math.round(turtle.getXCoordinate()));
    assertEquals(-35, Math.round(turtle.getYCoordinate()));
  }

  @Test
  void testMoveRightFrom135() {
    turtle.setOrientation(135);
    right.execute(turtle);
    assertEquals(35, Math.round(turtle.getXCoordinate()));
    assertEquals(35, Math.round(turtle.getYCoordinate()));
  }

  @Test
  void testMoveRightFrom225() {
    turtle.setOrientation(225);
    right.execute(turtle);
    assertEquals(-35, Math.round(turtle.getXCoordinate()));
    assertEquals(35, Math.round(turtle.getYCoordinate()));
  }

  @Test
  void testMoveRightFrom315() {
    turtle.setOrientation(315);
    right.execute(turtle);
    assertEquals(-35, Math.round(turtle.getXCoordinate()));
    assertEquals(-35, Math.round(turtle.getYCoordinate()));
  }

}