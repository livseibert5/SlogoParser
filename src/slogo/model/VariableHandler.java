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
}
