package slogo.model.controlcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.CommandBlock;
import slogo.model.Constant;
import slogo.model.Turtle;
import slogo.model.backendexceptions.MathException;

/**
 * Tests for IfElse class.
 *
 * @author Livia Seibert
 */
public class IfElseTest {

  private Controller controller;
  private Turtle turtle;
  private IfElse ifElseCommand;

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
    CommandBlock runFalse = new CommandBlock("fd 80");
    ifElseCommand = new IfElse(controller, trueVar, runTrue, runFalse);
    assertEquals(50, ifElseCommand.execute(turtle));
  }

  @Test
  void elseBlockExecutes()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, MathException {
    Constant trueVar = new Constant(0);
    CommandBlock runTrue = new CommandBlock("fd 50");
    CommandBlock runFalse = new CommandBlock("fd 80");
    ifElseCommand = new IfElse(controller, trueVar, runTrue, runFalse);
    assertEquals(80, ifElseCommand.execute(turtle));
  }
}
