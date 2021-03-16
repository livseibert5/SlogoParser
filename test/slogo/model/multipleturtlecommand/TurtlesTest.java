package slogo.model.multipleturtlecommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.Turtle;

/**
 * Tests for Turtles class.
 *
 * @author Livia Seibert
 */
public class TurtlesTest {

  private Turtles turtles;
  private Turtle turtle;
  private Controller controller;

  @BeforeEach
  void setUp() {
    controller = new Controller();
  }

  @Test
  void putNoTurtles() {
    turtles = new Turtles(controller);
    assertEquals(1, turtles.execute(turtle));
  }

  @Test
  void putFiveTurtles() {
    for (int i = 2; i < 6; i++) {
      controller.getTurtleHandler().addTurtle(new Turtle());
    }
    turtles = new Turtles(controller);
    assertEquals(5, turtles.execute(turtle));
  }
}
