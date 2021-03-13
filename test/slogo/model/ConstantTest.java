package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for Constant Class
 *
 * @author Rachel Luria
 */
public class ConstantTest {

  Constant constant;

  @Test
  void testConstantConstructor() {
    constant = new Constant(5);
    assertEquals(5, constant.getValue());
  }

}
