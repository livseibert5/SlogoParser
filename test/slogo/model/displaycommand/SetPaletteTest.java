package slogo.model.displaycommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.Color;
import slogo.model.Constant;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for SetPalette Class
 *
 * @author Rachel Luria
 */
public class SetPaletteTest {

  private Turtle turtle;
  private Controller controller;
  private SetPalette paletteCommand;

  @BeforeEach
  void setUp() {
    controller = new Controller();
    turtle = new Turtle();
  }

  //@Test
  void checkSetPaletteIndex3Orange() {
    paletteCommand = new SetPalette(controller, new Constant(3), new Constant(255),
        new Constant(69), new Constant(0));
    assertEquals(3, paletteCommand.execute(turtle));
    Color color = controller.getColorHandler().getColor(3);
    assertEquals(1.0, Math.round(color.getR() * 1000.0)/1000.0);
    assertEquals(0.271, Math.round(color.getG() * 1000.0)/1000.0);
    assertEquals(0, color.getB());
  }

  //@Test
  void checkSetPaletteIndex30Pink() {
    paletteCommand = new SetPalette(controller, new Constant(30), new Constant(255),
        new Constant(192), new Constant(203));
    assertEquals(30, paletteCommand.execute(turtle));
    Color color = controller.getColorHandler().getColor(30);
    assertEquals(1, color.getR());
    assertEquals(0.753, Math.round(color.getG() * 1000.0)/1000.0);
    assertEquals(0.796, Math.round(color.getB() * 1000.0)/1000.0);
  }

}
