package slogo.model.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    turtleHandler.addTurtle(turtle);
    assertEquals(turtleHandler.getTurtle(2), turtle);
  }

  @Test
  void getTurtleDoesntExists() {
    assertNotNull(turtleHandler.getTurtle(2));
  }
}
