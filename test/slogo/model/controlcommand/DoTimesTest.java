package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.CommandBlock;
import slogo.model.Turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoTimesTest {

  private Controller controller;
  private Turtle turtle;
  private DoTimes doTimes;

  @BeforeEach
  void setUp() {
    controller = new Controller();
    controller.getTurtleHandler().addTurtle(1, new Turtle());
    turtle = controller.getTurtleHandler().getTurtle(1);
  }

  @Test
  void doTimesWhenLimitLessThan1()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    CommandBlock limit = new CommandBlock(":lim 0");
    CommandBlock code = new CommandBlock("fd 50");
    doTimes = new DoTimes(controller, limit, code);
    assertEquals(0, doTimes.execute(turtle));
  }

  @Test
  void doTimesDoesExecute()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    CommandBlock limit = new CommandBlock(":lim 2");
    CommandBlock code = new CommandBlock("fd 50");
    doTimes = new DoTimes(controller, limit, code);
    assertEquals(50, doTimes.execute(turtle));
    assertEquals(100, turtle.getYCoordinate());
    assertEquals(0, turtle.getXCoordinate());
  }

  @Test
  void doTimesDoesExecuteUsesLoopVar()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    CommandBlock limit = new CommandBlock(":lim 2");
    CommandBlock code = new CommandBlock("rt :lim");
    doTimes = new DoTimes(controller, limit, code);
    assertEquals(2, doTimes.execute(turtle));
    assertEquals(87, turtle.getOrientation());
  }
}
