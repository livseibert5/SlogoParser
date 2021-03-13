package slogo.model.mathcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Tests for Product class.
 *
 * @author Rachel Luria
 */
public class ProductTest {

  private Turtle turtle = new Turtle();
  private Product product;

  @Test
  void testProductZeroes() {
    product = new Product(new Constant(0), new Constant(0));
    assertEquals(0, product.execute(turtle));
  }

  @Test
  void testProductSmallNumbers() {
    product = new Product(new Constant(4), new Constant(3));
    assertEquals(12, product.execute(turtle));
  }

  @Test
  void testProductargeNumbers() {
    product = new Product(new Constant(4381), new Constant(1893));
    assertEquals(8293233, product.execute(turtle));
  }

  @Test
  void testProductTwoNegativeNumbers() {
    product = new Product(new Constant(-10), new Constant(-33));
    assertEquals(330, product.execute(turtle));
  }

  @Test
  void testProductOneNegativeNumber() {
    product = new Product(new Constant(-10), new Constant(33));
    assertEquals(-330, product.execute(turtle));
  }

}


