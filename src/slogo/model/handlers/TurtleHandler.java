package slogo.model.handlers;

import java.util.Map;
import java.util.HashMap;
import slogo.model.Turtle;

public class TurtleHandler {

  Map<Integer, Turtle> turtles;

  public TurtleHandler() {
    turtles = new HashMap<>();
    turtles.put(1, new Turtle());
  }

  public void addTurtle(int id, Turtle turtle) {
    turtles.put(id, turtle);
  }

  public void removeTurtle(int id) {
    turtles.remove(id);
  }

  // TODO: once front end works, change this function to get a turtle with a specific id
  public Turtle getTurtle() {
    return turtles.get(0);
  }
}
