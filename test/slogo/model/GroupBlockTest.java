package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.backendexceptions.MathException;
import slogo.model.parser.Parser;

public class GroupBlockTest {

  private GroupBlock groupBlock;
  private Controller controller;
  private Parser parser;

  @BeforeEach
  void setUp(){
    controller = new Controller();
    groupBlock = new GroupBlock(controller);
    parser = new Parser(controller);
  }

  @Test
  void testOneParameterFunction() throws ClassNotFoundException {
    List<String> list = new ArrayList<>(Arrays.asList("fd", "30","40", "50", "60"));
    assertEquals("fd 30 fd 40 fd 50 fd 60", groupBlock.insertCommand(list));
  }

  @Test
  void testTwoParameterFunction() throws ClassNotFoundException {
    List<String> list = new ArrayList<>(Arrays.asList("sum", "30","40", "50", "60"));
    assertEquals("sum 30 sum 40 sum 50 60", groupBlock.insertCommand(list));
  }

  @Test
  void testWithParsing()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    assertEquals(100, parser.parse("fd ( sum 10 20 30 40 )"));
  }

  /*
  @Test
  void testWithLoop()
      throws NoSuchMethodException, InstantiationException, MathException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    assertEquals(150, parser.parse("( fd 50 ( sum 50 50 50 ) )"));
  }
   */
}
