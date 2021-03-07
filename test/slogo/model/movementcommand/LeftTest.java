package slogo.model.movementcommand;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeftTest {

  private Turtle turtle = new Turtle();
  private Left left;

  @Test
  void testMoveLeftFrom0() {
    turtle.setOrientation(0);
    left = new Left(new Constant(50));
    left.execute(turtle);
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(50, turtle.getYCoordinate());
  }

  @Test
  void testMoveLeftFrom90() {
    turtle.setOrientation(90);
    left = new Left(new Constant(50));
    left.execute(turtle);
    assertEquals(-50, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

  @Test
  void testMoveRightFrom180() {
    turtle.setOrientation(180);
    left = new Left(new Constant(50));
    left.execute(turtle);
    assertEquals(0, turtle.getXCoordinate());
    assertEquals(-50, turtle.getYCoordinate());
  }

  @Test
  void testMoveRightFrom270() {
    turtle.setOrientation(270);
    left = new Left(new Constant(50));
    left.execute(turtle);
    assertEquals(50, turtle.getXCoordinate());
    assertEquals(0, turtle.getYCoordinate());
  }

}
