package slogo.model.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import slogo.Observable;
import slogo.model.Turtle;

/**
 * TurtleHandler keeps a map of turtle's and their ids and handles any data manipulation of that
 * map.
 *
 * @author Livia Seibert
 */
public class TurtleHandler extends Observable<Object> {

  private final Map<Integer, Turtle> turtles;
  private List<Turtle> activeTurtles;
  private int currentTurtleIndex;

  /**
   * Constructor for TurtleHandler creates new map for the turtles.
   */
  public TurtleHandler() {
    turtles = new HashMap<>();
    turtles.put(1, new Turtle());
    activeTurtles = new ArrayList<>();
    activeTurtles.add(turtles.get(1));
    currentTurtleIndex = 1;
  }

  /**
   * Allows the front end and the back end to both add new turtles to the map.
   *
   * @param turtle new turtle object to add
   */
  public void addTurtle(Turtle turtle) {
    currentTurtleIndex++;
    turtle.addMultipleListeners(getListeners());
    notifyListeners("addTurtle", turtles, turtles.put(currentTurtleIndex, turtle));
  }

  public List<Turtle> getActiveTurtles() {
    return activeTurtles;
  }

  public int getNumberTurtles() {
    return turtles.size();
  }

  public List<Turtle> getAllTurtles() {
    return new ArrayList<>(turtles.values());
  }

  /**
   * Returns list of all turtle ids.
   *
   * @return List<Integer> of turtle IDs.
   * @author Jessica Yang
   */
  public List<Integer> getAllIds() {
    return new ArrayList<>(turtles.keySet());
  }

  public void setActiveTurtles(List<Turtle> newActiveTurtles) {
    notifyListeners("activeTurtles", activeTurtles, newActiveTurtles);
    activeTurtles = newActiveTurtles;
  }

  public int getTurtleId(Turtle turtle) {
    for (int key: turtles.keySet()) {
      if (turtles.get(key).equals(turtle)) {
        return key;
      }
    }
    return -1;
  }

  // TODO: once front end works, change this function to get a turtle with a specific id
  public Turtle getTurtle(int id) {
    if (turtles.containsKey(id)) {
      return turtles.get(id);
    } else {
      Turtle newTurtle = new Turtle();
      addTurtle(newTurtle);
      return newTurtle;
    }
  }
}
