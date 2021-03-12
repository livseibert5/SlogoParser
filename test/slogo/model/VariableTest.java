package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for Variable Class
 *
 * @author Rachel Luria
 */
public class VariableTest {

  Variable var;

  @Test
  void testVariableGetName(){
    var = new Variable("move");
    assertEquals("move", var.getName());
  }

  @Test
  void testGetValue() {
    var = new Variable("move", 33);
    assertEquals(33, var.getValue());
  }

  @Test
  void testSetValue() {
    var = new Variable("move", 33);
    var.setValue(44);
    assertEquals(44, var.getValue());

  }

}
