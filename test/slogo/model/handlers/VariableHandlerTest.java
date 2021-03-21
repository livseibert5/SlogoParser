package slogo.model.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Variable;

/**
 * Tests for VariableHandler class.
 *
 * @author Livia Seibert
 */
public class VariableHandlerTest {

  private VariableHandler variableHandler;

  @BeforeEach
  void setUp() {
    variableHandler = new VariableHandler();
  }

  @Test
  void addVariableWorks() {
    Variable variable = new Variable(":var");
    variableHandler.addVariable(variable);
    assertEquals(variable, variableHandler.getVariableWithName(":var"));
  }

  @Test
  void addVariableWithSameNameChangesVal() {
    Variable variable = new Variable(":var", 10);
    variableHandler.addVariable(variable);
    Variable newVar = new Variable(":var", 20);
    variableHandler.addVariable(newVar);
    assertEquals(1, variableHandler.getAllVariables().size());
  }
}
