package slogo.model.controlcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.CommandBlock;
import slogo.model.Turtle;

/**
 * Tests for MakeUserInstruction class.
 *
 * @author Livia Seibert
 */
public class MakeUserInstructionTest {

  private MakeUserInstruction makeUserInstruction;
  private Controller controller;
  private Turtle turtle;

  @BeforeEach
  void setUp() {
    controller = new Controller();
    turtle = controller.getTurtleHandler().getTurtle(1);
  }

  @Test
  void makeNewCommand()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    String name = "dash";
    CommandBlock header = new CommandBlock(":count");
    CommandBlock body = new CommandBlock("repeat :count [ pu fd 4 pd fd 4 ]");
    makeUserInstruction = new MakeUserInstruction(controller, name, header, body);
    assertEquals(1, makeUserInstruction.execute(turtle));
  }
}
