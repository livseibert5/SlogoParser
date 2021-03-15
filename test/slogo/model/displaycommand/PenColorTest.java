package slogo.model.displaycommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PenColorTest {

  private Turtle turtle;
  private PenColor penColor = new PenColor();

  @BeforeEach
  void setUp() {
    turtle = new Turtle();
  }

  @Test
  void checkReturnsCorrectPenColor() {
    turtle.setPenColor(6);
    assertEquals(6, penColor.execute(turtle));
  }
}
