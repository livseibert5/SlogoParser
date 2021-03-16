package slogo.model.handlers;

import java.util.Map;
import java.util.HashMap;
import slogo.model.Turtle;

/**
 * TurtleHandler keeps a map of turtle's and their ids and handles any data manipulation of that
 * map.
 *
 * @author Livia Seibert
 */
public class TurtleHandler {

  private Map<Integer, Turtle> turtles;
  private Turtle activeTurtle;
  private int currentTurtleIndex;

  /**
   * Constructor for TurtleHandler creates new map for the turtles.
   */
  public TurtleHandler() {
    turtles = new HashMap<>();
    turtles.put(1, new Turtle());
    activeTurtle = turtles.get(1);
    currentTurtleIndex = 1;
  }

  /**
   * Allows the front end and the back end to both add new turtles to the map.
   *
   * @param id     id of new turtle
   * @param turtle new turtle object to add
   */
  public void addTurtle(Turtle turtle) {
    currentTurtleIndex++;
    turtles.put(currentTurtleIndex, turtle);
  }

  /**
   * Allows the front end and the back end to remove the turtle with the given id.
   *
   * @param id id of turtle to remove
   */
  public void removeTurtle(int id) {
    turtles.remove(id);
  }

  public int getNumberTurtles() {
    return turtles.size();
  }

  public Turtle getActiveTurtle() {
    return activeTurtle;
  }

  // TODO: once front end works, change this function to get a turtle with a specific id
  public Turtle getTurtle(int id) {
    return turtles.get(id);
  }
}
