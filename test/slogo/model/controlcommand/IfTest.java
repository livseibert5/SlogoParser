package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.CommandBlock;
import slogo.model.Constant;
import slogo.model.Turtle;
import slogo.model.backendexceptions.MathException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for If class.
 *
 * @author Livia Seibert
 */
public class IfTest {

  private Controller controller;
  private Turtle turtle;
  private If ifCommand;

  @BeforeEach
  void setUp() {
    controller = new Controller();
    turtle = controller.getTurtleHandler().getTurtle(1);
  }

  @Test
  void ifBlockExecutes()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, MathException {
    Constant trueVar = new Constant(1);
    CommandBlock runTrue = new CommandBlock("fd 50");
    ifCommand = new If(controller, trueVar, runTrue);
    assertEquals(50, ifCommand.execute(turtle));
  }

  @Test
  void ifBlockDoesntExecute()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, MathException {
    Constant trueVar = new Constant(0);
    CommandBlock runTrue = new CommandBlock("fd 50");
    ifCommand = new If(controller, trueVar, runTrue);
    assertEquals(0, ifCommand.execute(turtle));
  }
}
