package slogo.model.multipleturtlecommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for ID class.
 *
 * @author Livia Seibert
 */
public class IDTest {

  private ID id;
  private Controller controller;

  @BeforeEach
  void setUp() {
    controller = new Controller();
    id = new ID(controller);
  }

  @Test
  void checkInitialTurtleID() {
    assertEquals(1, id.execute(controller.getTurtleHandler().getActiveTurtles().get(0)));
  }
}
