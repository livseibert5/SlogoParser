package slogo.model.displaycommand;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for SetPenColor class.
 *
 * @author Livia Seibert
 */
public class SetPenColorTest {

  private SetPenColor setPenColor;

  @Test
  void setToColor() {
    Constant value = new Constant(5);
    setPenColor = new SetPenColor(value);
    assertEquals(5, setPenColor.execute(new Turtle()));
  }
}
