package slogo.controller;

import java.util.ArrayList;
import java.util.List;
import slogo.model.TurtleHandler;

public class Controller {
  List<Variable> variables = new ArrayList<>();

  private TurtleHandler turtleHandler;

  public Controller() {
    turtleHandler = new TurtleHandler();
  }

  public Controller(Variable variable) {
    variables.add(variable);}

  public TurtleHandler getTurtleHandler() {
    return turtleHandler;
  }
}
