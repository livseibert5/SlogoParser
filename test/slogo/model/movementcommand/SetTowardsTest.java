package slogo.model.movementcommand;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for SetTowards class.
 *
 * @author Rachel Luria
 */
public class SetTowardsTest {

  private Turtle turtle = new Turtle();
  private SetTowards setTowards;

  @Test
  public void testSetTowardsPositiveXAxis(){
    turtle.setOrientation(0);
    setTowards = new SetTowards(new Constant(10), new Constant(0));
    setTowards.execute(turtle);
    assertEquals(0, turtle.getOrientation());
  }

  @Test
  public void testSetTowardsNegativeXAxis(){
    turtle.setOrientation(0);
    setTowards = new SetTowards(new Constant(-10), new Constant(0));
    setTowards.execute(turtle);
    assertEquals(180, turtle.getOrientation());
  }

  @Test
  public void testSetTowardsPositiveYAxis(){
    turtle.setOrientation(0);
    setTowards = new SetTowards(new Constant(0), new Constant(10));
    setTowards.execute(turtle);
    assertEquals(90, turtle.getOrientation());
  }

  @Test
  public void testSetTowardsNegativeYAxis(){
    turtle.setOrientation(0);
    setTowards = new SetTowards(new Constant(0), new Constant(-10));
    setTowards.execute(turtle);
    assertEquals(270, turtle.getOrientation());
  }

  @Test
  public void testSetTowardsFirstQuadrant(){
    turtle.setOrientation(90);
    setTowards = new SetTowards(new Constant(3), new Constant(2));
    setTowards.execute(turtle);
    assertEquals(33, turtle.getOrientation());
  }

  @Test
  public void testSetTowardsSecondQuadrant(){
    turtle.setOrientation(3);
    setTowards = new SetTowards(new Constant(-3), new Constant(2));
    setTowards.execute(turtle);
    assertEquals(147, turtle.getOrientation());
  }

  @Test
  public void testSetTowardsThirdQuadrant(){
    turtle.setOrientation(170);
    setTowards = new SetTowards(new Constant(-3), new Constant(-2));
    setTowards.execute(turtle);
    assertEquals(213, turtle.getOrientation());
  }

  @Test
  public void testSetTowardsFourthQuadrant(){
    turtle.setOrientation(0);
    setTowards = new SetTowards(new Constant(3), new Constant(-2));
    setTowards.execute(turtle);
    assertEquals(327, turtle.getOrientation());
  }

}
