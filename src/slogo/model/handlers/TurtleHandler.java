package slogo.model.handlers;

import java.beans.PropertyChangeListener;
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
  private PropertyChangeListener backEndX;
  private PropertyChangeListener backEndY;
  private PropertyChangeListener backEndOrientation;

  /**
   * Constructor for TurtleHandler creates new map for the turtles.
   */
  public TurtleHandler() {
    turtles = new HashMap<>();
    turtles.put(1, new Turtle());
    activeTurtles = new ArrayList<>();
    activeTurtles.add(turtles.get(1));
    currentTurtleIndex = 1;
    setUpListeners();
    activeTurtles.forEach(turtle -> {
      turtle.addListener(backEndX);
      turtle.addListener(backEndY);
      turtle.addListener(backEndOrientation);
    });
  }

  /**
   * Creates listeners to allow the back end to update the turtle's location based on front end key
   * input.
   */
  private void setUpListeners() {
    backEndY = event -> {
      if (event.getPropertyName().equals("backEndYCoordinate")) {
        activeTurtles.forEach(turtle -> turtle.setLocation(new double[]{turtle.getXCoordinate(),
            turtle.getYCoordinate() + (Double) event.getNewValue()}));
      }
    };
    backEndX = event -> {
      if (event.getPropertyName().equals("backEndXCoordinate")) {
        activeTurtles.forEach(turtle -> turtle.setLocation(
            new double[]{turtle.getXCoordinate() + (Double) event.getNewValue(),
                turtle.getYCoordinate()}));
      }
    };
    backEndOrientation = event -> {
      if (event.getPropertyName().equals("frontEndOrientation")) {
        activeTurtles.forEach(turtle -> turtle
            .setOrientation(turtle.getOrientation() + (Double) event.getNewValue()));
      }
    };
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

  /**
   * Allows access to the list of active turtles.
   *
   * @return list of active turtles
   */
  public List<Turtle> getActiveTurtles() {
    return activeTurtles;
  }

  /**
   * Allows access to number of turtles that exist.
   *
   * @return number of turtles created
   */
  public int getNumberTurtles() {
    return turtles.size();
  }

  /**
   * Returns a list of all the turtles creates.
   *
   * @return list of all turtles
   */
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

  /**
   * Sets active turtles, applies the listeners to them.
   *
   * @param newActiveTurtles turtles that should listen to commands
   */
  public void setActiveTurtles(List<Turtle> newActiveTurtles) {
    notifyListeners("activeTurtles", activeTurtles, newActiveTurtles);
    activeTurtles = newActiveTurtles;
    turtles.forEach((id, turtle) -> {
      turtle.removeListener(backEndX);
      turtle.removeListener(backEndY);
      turtle.removeListener(backEndOrientation);
    });
    activeTurtles.forEach(turtle -> {
      turtle.addListener(backEndX);
      turtle.addListener(backEndY);
      turtle.addListener(backEndOrientation);
    });
  }

  /**
   * Returns ID of the given turtle.
   *
   * @param turtle turtle to retrieve id of
   * @return id of turtle
   */
  public int getTurtleId(Turtle turtle) {
    for (int key : turtles.keySet()) {
      if (turtles.get(key).equals(turtle)) {
        return key;
      }
    }
    return -1;
  }

  /**
   * Retrieves turtle with the given id.
   *
   * @param id id of turtle to return
   * @return turtle object with given id
   */
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
