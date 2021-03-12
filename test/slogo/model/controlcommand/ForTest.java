package slogo.model.controlcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.CommandBlock;
import slogo.model.Turtle;
import slogo.model.backendexceptions.MathException;

/**
 * Tests for For class.
 *
 * @author Livia Seibert
 */
public class ForTest {

  private For forCommand;
  private Turtle turtle;
  private Controller controller;

  @BeforeEach
  void setUp() {
    controller = new Controller();
    controller.getTurtleHandler().addTurtle(1, new Turtle());
    turtle = controller.getTurtleHandler().getTurtle(1);
  }

  @Test
  void forWithVariables()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    CommandBlock header = new CommandBlock(":dist 1 5 1");
    CommandBlock body = new CommandBlock("fd :dist rt product :dist 3");
    forCommand = new For(controller, header, body);
    assertEquals(12, forCommand.execute(turtle));
  }
}
