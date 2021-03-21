package slogo.model.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Color;
import slogo.model.Turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests for ColorHandler Class
 *
 * @author Rachel Luria
 */
public class ColorHandlerTest {

  private ColorHandler colorHandler;


  @BeforeEach
  void setUp() {
    colorHandler = new ColorHandler();
  }

  @Test
  void testDefaultBlack(){
    Color color = colorHandler.getColor(0);
    assertEquals(0, color.getR());
    assertEquals(0, color.getG());
    assertEquals(0, color.getB());
  }

  //@Test
  void testDefaultRed(){
    Color color = colorHandler.getColor(1);
    assertEquals(1, color.getR());
    assertEquals(0, color.getG());
    assertEquals(0, color.getB());
  }



}
