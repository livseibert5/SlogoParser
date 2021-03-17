package slogo.model.displaycommand;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for SetPenShape class.
 *
 * @author Rachel Luria
 */
public class SetPenShapeTest {

  private SetPenShape setPenShape;

  @Test
  void setPenToShape() {
    Constant value = new Constant(0);
    setPenShape = new SetPenShape(value);
    assertEquals(0, setPenShape.execute(new Turtle()));
  }
}
