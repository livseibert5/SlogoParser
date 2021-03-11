package slogo.model.handlers;

import java.util.ArrayList;
import java.util.List;
import slogo.model.Variable;

/**
 * VariableHandler stores user defined variables in a list so that the back end can use them
 * and the front end can display them.
 *
 * @author Livia Seibert
 */
public class VariableHandler {

  List<Variable> variables;

  /**
   * VariableHandler constructor declares new list to store variables in.
   */
  public VariableHandler() {
    variables = new ArrayList<>();
  }

  /**
   * Lets the parser add new variables to the handler when they are created by the user.
   *
   * @param variable new variable object to add
   */
  public void addVariable(Variable variable) {
    variables.add(variable);
  }

  /**
   * Retrieves the value of the variable with the given name.
   *
   * @param name name of variable to get value of
   * @return value of variable with given name, -1 otherwise
   */
  public double getVariableValueWithName(String name) {
    for (Variable var: variables) {
      if (var.getName().equals(name)) {
        return var.getValue();
      }
    }
    return -1;
  }

  /**
   * Retrieves the variable with the given name so that the parser can use its value
   * in execution.
   *
   * @param name name of the variable to retrieve
   * @return variable object with the given name
   */
  public Variable getVariableWithName(String name) {
    for (Variable var: variables) {
      if (var.getName().equals(name)) {
        return var;
      }
    }
    return null;
  }

  public void removeVariableWithName(String name) {
    for (Variable var: variables) {
      if (var.getName().equals(name)) {
        variables.remove(var);
        break;
      }
    }
  }
}
