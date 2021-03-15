package slogo.model.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.backendexceptions.MathException;

/**
 * Tests for Parser class.
 *
 * @author Livia Seibert
 */
public class ParserTest {

  private Parser parser;

  @BeforeEach
  void setUp() {
    parser = new Parser(new Controller());
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
        + "]\n";
    assertEquals(1, parser.parse(command));
  }

}
