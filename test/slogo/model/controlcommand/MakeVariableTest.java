package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.Constant;
import slogo.model.Turtle;
import slogo.model.Variable;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for MakeUserVariable class.
 *
 * @author Livia Seibert
 */
public class MakeVariableTest {

  private MakeVariable makeVariable;
  private Controller controller;
  private Turtle turtle;

  @BeforeEach
  void setUp() {
    controller = new Controller();
    controller.getTurtleHandler().addTurtle(1, new Turtle());
    turtle = controller.getTurtleHandler().getTurtle(1);
  }

  @Test
  void makeNewVariable()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    Variable variable = new Variable(":repeat");
    Constant value = new Constant(10);
    makeVariable = new MakeVariable(controller, variable, value);
    assertEquals(10, makeVariable.execute(turtle));
  }
}
