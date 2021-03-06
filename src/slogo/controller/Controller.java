package slogo.controller;

import slogo.model.TurtleHandler;

public class Controller {

  private TurtleHandler turtleHandler;

  public Controller() {
    turtleHandler = new TurtleHandler();
  }

  public TurtleHandler getTurtleHandler() {
    return turtleHandler;
  }
}
