package slogo.model.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;

public class ParserTest {

  private Parser parser = new Parser(new Controller());

  @Test
  void testFd50()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    assertEquals(50, parser.parse("fd 50"));
  }

  @Test
  void testForward70HardWay()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    assertEquals(70, parser.parse("fd sum sum sum sum 10 20 30 5 5"));
  }

  @Test
  void testForward70OtherHardWay()
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    assertEquals(70, parser.parse("fd sum 10 sum 10 sum 10 sum 20 20"));
  }
}
