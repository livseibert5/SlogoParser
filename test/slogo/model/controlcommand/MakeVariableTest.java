package slogo.model.controlcommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.Turtle;

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

}
