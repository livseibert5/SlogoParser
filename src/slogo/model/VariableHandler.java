package slogo.model;

import java.util.ArrayList;
import java.util.List;

public class VariableHandler {

  List<Variable> variables;

  public VariableHandler() {
    variables = new ArrayList<>();
  }

  public void addVariable(Variable variable) {
    variables.add(variable);
  }

  public double getVariableValueWithName(String name) {
    for (Variable var: variables) {
      if (var.getName().equals(name)) {
        return var.getValue();
      }
    }
    return -1;
  }

  public Variable getVariableWithName(String name) {
    for (Variable var: variables) {
      if (var.getName().equals(name)) {
        return var;
      }
    }
    return null;
  }
}
