package slogo.model.displaycommand;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for SetPenSize class.
 *
 * @author Livia Seibert
 */
public class SetPenSizeTest {

  private SetPenSize setPenSize;

  @Test
  void setPenToColor() {
    Constant value = new Constant(5);
    setPenSize = new SetPenSize(value);
    assertEquals(5, setPenSize.execute(new Turtle()));
  }
}
