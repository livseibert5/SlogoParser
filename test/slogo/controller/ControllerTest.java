package slogo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Controller class.
 *
 * @author Livia Seibert
 */
public class ControllerTest {

  private Controller controller;

  @BeforeEach
  void setUp() {
    controller = new Controller();
  }

  @Test
  void doesGetVariableHandler() {
    assertNotNull(controller.getVariableHandler());
  }

  @Test
  void doesGetTurtleHandler() {
    assertNotNull(controller.getTurtleHandler());
  }

  @Test
  void doesGetUserDefinedCommandHandler() {
    assertNotNull(controller.getUserDefinedCommandHandler());
  }

  @Test
  void doesGetColorHandler() {
    assertNotNull(controller.getColorHandler());
  }

  @Test
  void setLanguage() {
    controller.setLanguage("Spanish");
    assertEquals("Spanish", controller.getLanguage());
  }
}
