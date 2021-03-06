package slogo.model.handlers;

import java.util.List;
import javafx.collections.FXCollections;
import slogo.model.Variable;

/**
 * VariableHandler stores user defined variables in a list so that the back end can use them and the
 * front end can display them.
 *
 * @author Livia Seibert
 */
public class VariableHandler {

  List<Variable> variables;

  /**
   * VariableHandler constructor declares new list to store variables in.
   */
  public VariableHandler() {
    variables = FXCollections.observableArrayList();
  }

  /**
   * Lets the parser add new variables to the handler when they are created by the user.
   *
   * @param variable new variable object to add
   */
  public void addVariable(Variable variable) {
    if (getVariableWithName(variable.getName()) != null) {
      getVariableWithName(variable.getName()).setValue(variable.getValue());
    } else {
      variables.add(variable);
    }
  }

  /**
   * Returns list of all user defined variables.
   *
   * @return list of all user defined variables
   */
  public List<Variable> getAllVariables() {
    return variables;
  }

  /**
   * Retrieves the value of the variable with the given name.
   *
   * @param name name of variable to get value of
   * @return value of variable with given name, -1 otherwise
   */
  public double getVariableValueWithName(String name) {
    for (Variable var : variables) {
      if (var.getName().equals(name)) {
        return var.getValue();
      }
    }
    return -1;
  }

  /**
   * Retrieves the variable with the given name so that the parser can use its value in execution.
   *
   * @param name name of the variable to retrieve
   * @return variable object with the given name
   */
  public Variable getVariableWithName(String name) {
    for (Variable var : variables) {
      if (var.getName().equals(name)) {
        return var;
      }
    }
    return null;
  }

  /**
   * Removes the variable object with the given name.
   *
   * @param name name of variable to remove
   */
  public void removeVariableWithName(String name) {
    for (Variable var : variables) {
      if (var.getName().equals(name)) {
        variables.remove(var);
        break;
      }
    }
  }
}
