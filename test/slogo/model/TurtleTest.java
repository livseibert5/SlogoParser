package slogo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Turtle class.
 *
 * @author Livia Seibert
 */
public class TurtleTest {

  private Turtle turtle;

  @BeforeEach
  void setUp() {
    turtle = new Turtle();
  }

  @Test
  void setOrientationGreaterThan360() {
    turtle.setOrientation(365);
    assertEquals(5, turtle.getOrientation());
  }

  @Test
  void setOrientationLessThan0() {
    turtle.setOrientation(-5);
    assertEquals(355, turtle.getOrientation());
  }

  @Test
  void setOrientationInProperRange() {
    turtle.setOrientation(78);
    assertEquals(78, turtle.getOrientation());
  }

  @Test
  void setLocationGetLocationAsArray() {
    turtle.setLocation(new double[]{5, 10});
    assertEquals(5, turtle.getLocation()[0]);
    assertEquals(10, turtle.getLocation()[1]);
  }

  @Test
  void setLocationGetLocationByCoordinate() {
    turtle.setLocation(new double[]{8, 3});
    assertEquals(8, turtle.getXCoordinate());
    assertEquals(3, turtle.getYCoordinate());
  }

  @Test
  void setPenDownSetsBooleanToTrue() {
    turtle.setPenDown();
    assertEquals(true, turtle.penIsDown());
  }

  @Test
  void setPenUpSetsBooleanToFalse() {
    turtle.setPenUp();
    assertEquals(false, turtle.penIsDown());
  }

  @Test
  void showTurtleSetsBooleanToTrue() {
    turtle.showTurtle();
    assertEquals(true, turtle.isShowing());
  }

  @Test
  void hideTurtleSetsBooleanToFalse() {
    turtle.hideTurtle();
    assertEquals(false, turtle.isShowing());
  }

  @Test
  void setPenColor() {
    turtle.setPenColor(8);
    assertEquals(8, turtle.getPenColor());
  }

  @Test
  void setPenThickness() {
    turtle.setPenThickness(6);
    assertEquals(6, turtle.getPenThickness());
  }

  @Test
  void setLocationOutOfBoundsPosY() {
    turtle.setLocation(new double[]{0, 1000});
    assertEquals(300, turtle.getYCoordinate());
  }

  @Test
  void setLocationOutOfBoundsPosX() {
    turtle.setLocation(new double[]{10000, 0});
    assertEquals(300, turtle.getXCoordinate());
  }

  @Test
  void setLocationOutOfBoundsNegY() {
    turtle.setLocation(new double[]{0, -1000});
    assertEquals(-300, turtle.getYCoordinate());
  }

  @Test
  void setLocationOutOfBoundsNegX() {
    turtle.setLocation(new double[]{-10000, 0});
    assertEquals(-300, turtle.getXCoordinate());
  }
}
