package slogo.model.movementcommand;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for SetHeading class.
 *
 * @author Rachel Luria
 */
public class SetHeadingTest {

  private Turtle turtle = new Turtle();
  private SetHeading setHeading;

  @Test
  public void test0Degrees(){
    turtle.setOrientation(0);
    setHeading = new SetHeading(new Constant(0));
    setHeading.execute(turtle);
    assertEquals(0, turtle.getOrientation());
  }

  @Test
  public void test90Degrees(){
    turtle.setOrientation(0);
    setHeading = new SetHeading(new Constant(90));
    setHeading.execute(turtle);
    assertEquals(90, turtle.getOrientation());
  }

  @Test
  public void test180Degrees(){
    turtle.setOrientation(0);
    setHeading = new SetHeading(new Constant(180));
    setHeading.execute(turtle);
    assertEquals(180, turtle.getOrientation());
  }

  @Test
  public void test270Degrees(){
    turtle.setOrientation(0);
    setHeading = new SetHeading(new Constant(270));
    setHeading.execute(turtle);
    assertEquals(270, turtle.getOrientation());
  }

}
