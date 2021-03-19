package slogo.model.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.Turtle;
import slogo.model.backendexceptions.MathException;

/**
 * Tests for Parser class.
 *
 * @author Livia Seibert
 */
public class ParserTest {

  private Parser parser;
  private Controller controller;

  @BeforeEach
  void setUp() {
    this.controller = new Controller();
    parser = new Parser(controller);
  }

  @Test
  void testFd50()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, MathException {
    assertEquals(50, parser.parse("fd 50"));
  }

  @Test
  void testForward70HardWay()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, MathException {
    assertEquals(70, parser.parse("fd sum sum sum sum 10 20 30 5 5"));
  }

  @Test
  void testForward70OtherHardWay()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, MathException {
    assertEquals(70, parser.parse("fd sum 10 sum 10 sum 10 sum 20 20"));
  }

  @Test
  void nestedRepeats()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    assertEquals(40, parser.parse("repeat 3 [ repeat 2 [ fd 1 rt 2 ] rt 40 ]"));
  }

  @Test
  void userDefinedCommand()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    assertEquals(1, parser.parse("to dash [ :count ] [ repeat :count [ pu fd 4 pd fd 4 ] ]"));
    assertEquals(4, parser.parse("dash 10"));
  }

  @Test
  void userDefinedVariable()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    assertEquals(101, parser.parse("make :random sum 1 100"));
    assertEquals(101, parser.parse("fd :random"));
  }

  @Test
  void doTimes()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    assertEquals(0, parser.parse("dotimes [ :t 2 ] [ fd 1 rt / sin :t 2 ]"));
  }

  @Test
  void forwardRight()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    assertEquals(100, parser.parse("fd rt 100"));
  }

  @Test
  void userVariableForward()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    assertEquals(4, parser.parse("make :dist 4"));
    assertEquals(12, parser.parse("fd :dist rt product :dist 3 "));
  }

  @Test
  void forLoop()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    assertEquals(12, parser.parse("for [ :dist 1 5 1 ] [ fd :dist rt product :dist 3 ]"));
  }

  @Test
  void removesComments()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    String command = "# fd 30\nfd 50";
    assertEquals(50, parser.parse(command));
  }

  @Test
  void RecursionSpiral()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    String command = "to spiral [ :len ]\n"
        + "[\n"
        + "  if less? :len 200\n"
        + "  [\n"
        + "    fd :len\n"
        + "    rt 89\n"
        + "    spiral + :len 3\n"
        + "  ]\n"
        + "]";
    assertEquals(1, parser.parse(command));
    assertEquals(1, parser.parse("spiral 10"));
  }

  @Test
  void RecursionNotLoop()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    String command = "to example [ :x ]\n"
        + "[\n"
        + "  if greater? :x 10\n"
        + "  [\n"
        + "    example difference :x 10\n"
        + "  ]\n"
        + "  fd 50\n"
        + "  right 10\n"
        + "]\n";
    assertEquals(1, parser.parse(command));
    assertEquals(10, parser.parse("example 100"));
  }


  @Test
  void tellTest()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    String command = "tell [ 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 ]\n"
        + "rt random 360\n"
        + "fd 100";
    assertEquals(100, parser.parse(command));
  }

  @Test
  void askWithTest()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    String command = "tell [ 1 2 3 4 5 6 7 8 ]" + " askwith [ greater? xcor 50 ] [\n"
        + "  lt random 360\n"
        + "  bk 100\n"
        + "]";
    assertEquals(0, parser.parse(command));
  }

  @Test
  void askTest()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    List<Turtle> turtles = new ArrayList<>();
    turtles.add(controller.getTurtleHandler().getTurtle(1));
    for (int i = 1; i < 20; i++) {
      Turtle newTurtle = new Turtle();
      controller.getTurtleHandler().addTurtle(newTurtle);
      turtles.add(newTurtle);
    }
    controller.getTurtleHandler().setActiveTurtles(turtles);
    String command = "ask [ 1 8 2 ] [ \n"
        + "  bk 100\n"
        + "]";
    assertEquals(100, parser.parse(command));
    assertEquals(-100, controller.getTurtleHandler().getAllTurtles().get(0).getYCoordinate());
    assertEquals(-100, controller.getTurtleHandler().getAllTurtles().get(1).getYCoordinate());
    assertEquals(-100, controller.getTurtleHandler().getAllTurtles().get(7).getYCoordinate());
    assertEquals(0, controller.getTurtleHandler().getAllTurtles().get(6).getYCoordinate());
  }

  @Test
  void badCommandThenGoodCommand()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    String badCommand = "xyz 20";
    String goodCommand = "fd 50";
    assertThrows(Exception.class, () -> {
      parser.parse(badCommand);
    });
    assertEquals(50, parser.parse(goodCommand));
  }
}
