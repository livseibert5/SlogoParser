package slogo.model;

import java.util.Map;
import java.util.HashMap;

public class TurtleHandler {

  Map<Integer, Turtle> turtles;

  public TurtleHandler() {
    turtles = new HashMap<>();
  }

  public void addTurtle() {

  }

  public Turtle getTurtle() {
    return new Turtle();
  }
}
