package slogo.model.multipleturtlecommand;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.CommandBlock;
import slogo.model.backendexceptions.MathException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Tell class.
 *
 * @author Livia Seibert
 */
public class TellTest {

  private Tell tell;
  private Controller controller;

  @BeforeEach
  void setUp() {
    controller = new Controller();
  }

  @Test
  void testTurtlesDontExist()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    CommandBlock turtleBlock = new CommandBlock("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20");
    tell = new Tell(controller, turtleBlock);
    assertEquals(20, tell.execute(controller.getTurtleHandler().getTurtle(1)));
  }
}
