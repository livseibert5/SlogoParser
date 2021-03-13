package slogo.model.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests for TurtleHandler class.
 *
 * @author Livia Seibert
 */
public class TurtleHandlerTest {

  private TurtleHandler turtleHandler;

  @BeforeEach
  void setUp() {
    turtleHandler = new TurtleHandler();
  }

  @Test
  void addNewTurtle() {
    Turtle turtle = new Turtle();
    turtleHandler.addTurtle(4, turtle);
    assertEquals(turtleHandler.getTurtle(4), turtle);
  }

  @Test
  void deleteTurtle() {
    Turtle turtle = new Turtle();
    turtleHandler.addTurtle(4, turtle);
    turtleHandler.removeTurtle(4);
    assertNull(turtleHandler.getTurtle(4));
  }
}
