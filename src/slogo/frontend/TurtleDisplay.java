package slogo.frontend;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import slogo.model.Turtle;

/**
 * Updates the front-end representation of a turtle.
 */
public class TurtleDisplay implements PropertyChangeListener {

  private final Map<Integer, Turtle> turtleMap = new HashMap<>();
  private final Map<Integer, ImageView> turtleViewMap = new HashMap<>();
  private String DEFAULT_IMAGE_PATH = "/" + (TurtleDisplay.class.getPackageName() + ".resources.images.").replace('.', '/');
  private String IMAGE_FILE = DEFAULT_IMAGE_PATH + "temp_turtle.jpg";

  private double dimensionSize;
  private final Group myRoot;

  private Color lineColor;

  /**
   * Constructor for TurtleDisplay. Takes in map of turtles from Controller.
   *
   * @param turtle from backend
   */
  public TurtleDisplay(Turtle turtle, Group root) {
    turtleMap.put(1, turtle);
    updateImageMap();
    myRoot = root;
    lineColor = Color.BLACK;
  }

  /**
   * Adds imageview equivalent for each turtle in turtleMap. TODO use observer
   */
  private void updateImageMap() {
    for (Integer i : turtleMap.keySet()) {
      if (turtleViewMap.get(i) == null) {
        addTurtleView(i);
      }
    }
  }

  /**
   * Instantiates imageview objects for turtles in turtleViewMap.
   */
  private void addTurtleView(int id) {
    Turtle toAddTurtle = turtleMap.get(id);
    ImageView turtleView = new ImageView();
    turtleView.setImage(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(IMAGE_FILE))));

    turtleView.setX(toAddTurtle.getXCoordinate());
    turtleView.setY(toAddTurtle.getYCoordinate());

    turtleView.setFitHeight(dimensionSize);
    turtleView.setFitWidth(dimensionSize);

    turtleView.setRotate(toAddTurtle.getOrientation());

    turtleViewMap.put(id, turtleView);
  }

  /**
   * Updates state of a given turtle. Assumes turtles in turtleMap have been updated. TODO use observer
   *
   * @param id turtle id in hashmaps
   */
  public void updateTurtleView(int id) {
    Turtle updatedTurtle = turtleMap.get(id);
    ImageView currTurtleView = turtleViewMap.get(id);

    if (updatedTurtle.penIsDown()) {
      drawNewLine(updatedTurtle.getLocation(), currTurtleView);
    }

    rotateTurtleView(updatedTurtle.getOrientation(), currTurtleView);
    moveTurtleView(updatedTurtle.getLocation(), currTurtleView);
    updateTurtleViewVisibility(updatedTurtle.isShowing(), currTurtleView);
  }

  /**
   * Changes visibility of TurtleView.
   *
   * @param visible boolean
   * @param currTurtleView needs to be updated view
   */
  private void updateTurtleViewVisibility(boolean visible, ImageView currTurtleView) {
    currTurtleView.setVisible(visible);
  }

  /**
   * Draws line from original location to new location.
   *
   * @param newLocation double array
   * @param currTurtleView needs to be updated view
   */
  private void drawNewLine(double[] newLocation, ImageView currTurtleView) {
    Line newLine = new Line();

    newLine.setFill(lineColor);
    newLine.setStroke(lineColor);
    newLine.setStartX(currTurtleView.getX());
    newLine.setStartY(currTurtleView.getY());
    newLine.setEndX(newLocation[0]);
    newLine.setEndY(newLocation[1]);

    myRoot.getChildren().add(newLine);
  }

  /**
   * Rotates TurtleView orientation.
   *
   * @param newOrientation boolean
   * @param currTurtleView needs to be updated view
   */
  private void rotateTurtleView(double newOrientation, ImageView currTurtleView) {
    currTurtleView.setRotate(newOrientation);
  }

  /**
   * Moves TurtleView.
   *
   * @param newLocation double array
   * @param currTurtleView needs to be updated view
   */
  private void moveTurtleView(double[] newLocation, ImageView currTurtleView) {
    currTurtleView.setX(newLocation[0]);
    currTurtleView.setY(newLocation[1]);
  }

  /**
   * Called by SceneBuilder to update line color from GUI. TODO
   *
   * @param newColor updated color
   */
  private void setLineColor(Color newColor) {
    lineColor = newColor;
    System.out.printf("new color!");
  }

  public void propertyChange(PropertyChangeEvent event) {
    setLineColor((Color) event.getNewValue());
  }
}
