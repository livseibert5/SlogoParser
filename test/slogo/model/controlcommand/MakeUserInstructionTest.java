package slogo.model.controlcommand;

import org.junit.jupiter.api.BeforeEach;
import slogo.controller.Controller;
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
    controller.getTurtleHandler().addTurtle(1, new Turtle());
    turtle = controller.getTurtleHandler().getTurtle(1);
  }
}
