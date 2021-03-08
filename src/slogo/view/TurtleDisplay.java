package slogo.view;

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
public class TurtleDisplay {

  private final Map<Integer, Turtle> turtleMap;
  private final Map<Integer, ImageView> turtleViewMap = new HashMap<>();
  private String IMAGE_FILE;

  private double dimensionSize;
  private final Group myRoot;

  private Color lineColor;

  /**
   * Constructor for TurtleDisplay. Takes in map of turtles from Controller.
   *
   * @param turtles from backend
   */
  public TurtleDisplay(Map<Integer, Turtle> turtles, Group root) {
    turtleMap = turtles;
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
    turtleView.setImage(new Image(Objects.requireNonNull(this.getClass().getClassLoader()
        .getResourceAsStream(IMAGE_FILE))));

    turtleView.setX(toAddTurtle.getXCoordinate());
    turtleView.setY(toAddTurtle.getYCoordinate());

    turtleView.setFitHeight(dimensionSize);
    turtleView.setFitWidth(dimensionSize);

    turtleView.setRotate(toAddTurtle.getOrientation());

    turtleViewMap.put(id, turtleView);
  }

  /**
   * Step function for updating turtle representation during animation.
   */
  public void step() {}

  /**
   * Updates state of a given turtle. Assumes turtles in turtleMap have been updated. TODO use observer
   *
   * @param id turtle id in hashmaps
   */
  private void updateTurtleView(int id) {
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
   * Changes visiblity of TurtleView.
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
  public void setLineColor(Color newColor) {
    lineColor = newColor;
  }
}
