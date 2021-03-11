package slogo.model.movementcommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Left class.
 *
 * @author Rachel Luria
 */
public class LeftTest {

  private Turtle turtle = new Turtle();
  private Left left;

  @BeforeEach
  void setUp() {
    left = new Left(new Constant(50));
  }

  @Test
  void testMoveLeftFrom0() {
    turtle.setOrientation(0);
    left.execute(turtle);
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(50, turtle.getYCoordinate());
  }

  @Test
  void testMoveLeftFrom90() {
    turtle.setOrientation(90);
    left.execute(turtle);
    assertEquals(-50, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void testMoveLeftFrom180() {
    turtle.setOrientation(180);
    left.execute(turtle);
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(-50, turtle.getYCoordinate());
  }

  @Test
  void testMoveLeftFrom270() {
    turtle.setOrientation(270);
    left.execute(turtle);
    assertEquals(50, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void testMoveLeftFrom45() {
    turtle.setOrientation(45);
    left.execute(turtle);
    assertEquals(-35, Math.round(turtle.getXCoordinate()));
    assertEquals(35, Math.round(turtle.getYCoordinate()));
  }

  @Test
  void testMoveLeftFrom135() {
    turtle.setOrientation(135);
    left.execute(turtle);
    assertEquals(-35, Math.round(turtle.getXCoordinate()));
    assertEquals(-35, Math.round(turtle.getYCoordinate()));
  }

  @Test
  void testMoveLeftFrom225() {
    turtle.setOrientation(225);
    left.execute(turtle);
    assertEquals(35, Math.round(turtle.getXCoordinate()));
    assertEquals(-35, Math.round(turtle.getYCoordinate()));
  }

  @Test
  void testMoveLeftFrom315() {
    turtle.setOrientation(315);
    left.execute(turtle);
    assertEquals(35, Math.round(turtle.getXCoordinate()));
    assertEquals(35, Math.round(turtle.getYCoordinate()));
  }



}
