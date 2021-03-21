package slogo.model.multipleturtlecommand;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.CommandBlock;
import slogo.model.Turtle;
import slogo.model.backendexceptions.MathException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the Ask class.
 *
 * @author Livia Seibert
 */
public class AskTest {

  private Controller controller;
  private Ask ask;

  @BeforeEach
  void setUp() {
    controller = new Controller();
    List<Turtle> turtles = new ArrayList<>();
    turtles.add(controller.getTurtleHandler().getTurtle(1));
    for (int i = 1; i < 20; i++) {
      Turtle newTurtle = new Turtle();
      controller.getTurtleHandler().addTurtle(newTurtle);
      turtles.add(newTurtle);
    }
    controller.getTurtleHandler().setActiveTurtles(turtles);
  }

  @Test
  void basicAsk()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    ask = new Ask(controller, new CommandBlock("1 2 3"), new CommandBlock("fd 50"));
    ask.execute(new Turtle());
    assertEquals(50, controller.getTurtleHandler().getAllTurtles().get(0).getYCoordinate());
    assertEquals(50, controller.getTurtleHandler().getAllTurtles().get(1).getYCoordinate());
    assertEquals(50, controller.getTurtleHandler().getAllTurtles().get(2).getYCoordinate());
    assertEquals(0, controller.getTurtleHandler().getAllTurtles().get(3).getYCoordinate());
  }
}
