package slogo.model.multipleturtlecommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.CommandBlock;
import slogo.model.Turtle;
import slogo.model.backendexceptions.MathException;

/**
 * Tests for AskWith class.
 *
 * @author Livia Seibert
 */
public class AskWithTest {
  private Controller controller;
  private AskWith askWith;

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
    askWith = new AskWith(controller, new CommandBlock("id equal? 3"), new CommandBlock("fd 50"));
    askWith.execute(new Turtle());
    assertEquals(0, controller.getTurtleHandler().getAllTurtles().get(1).getYCoordinate());
    assertEquals(50, controller.getTurtleHandler().getAllTurtles().get(2).getYCoordinate());
  }
}
