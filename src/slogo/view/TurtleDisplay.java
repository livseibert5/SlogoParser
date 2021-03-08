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
  private Map<Integer, ImageView> turtleViewMap = new HashMap<>();
  private String IMAGE_FILE;

  private double dimensionSize;
  private Group myRoot;

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
        addTurtleView(turtleMap.get(i));
      }
    }
  }

  /**
   * Instantiates imageview objects for turtles in turtleViewMap.
   */
  private void addTurtleView(Turtle turtle) {
    ImageView turtleView = new ImageView();
    turtleView.setImage(new Image(Objects.requireNonNull(this.getClass().getClassLoader()
        .getResourceAsStream(IMAGE_FILE))));

    turtleView.setX(turtle.getXCoordinate());
    turtleView.setY(turtle.getYCoordinate());

    turtleView.setFitHeight(dimensionSize);
    turtleView.setFitWidth(dimensionSize);

    turtleView.setRotate(turtle.getOrientation());
  }

  /**
   * Step function for updating turtle representation during animation.
   */
  public void step() {}

  /**
   * Updates movement of a given turtle. Assumes turtles in turtleMap have been updated.
   *
   * @param id turtle id in hashmaps
   */
  private void updateTurtleView(int id) {
    Turtle updatedTurtle = turtleMap.get(id);
    ImageView currTurtleView = turtleViewMap.get(id);

    if (updatedTurtle.penIsDown()) {
      drawNewLine(updatedTurtle, currTurtleView);
    }

    rotateTurtleView(updatedTurtle, currTurtleView);
    moveTurtleView(updatedTurtle, currTurtleView);
  }

  /**
   * Draws line from original location to new location.
   *
   * @param updatedTurtle already updated turtle object
   * @param currTurtleView needs to be updated view
   */
  private void drawNewLine(Turtle updatedTurtle, ImageView currTurtleView) {
    Line newLine = new Line();

    newLine.setFill(lineColor);
    newLine.setStroke(lineColor);
    newLine.setStartX(currTurtleView.getX());
    newLine.setStartY(currTurtleView.getY());
    newLine.setEndX(updatedTurtle.getLocation()[0]);
    newLine.setEndY(updatedTurtle.getLocation()[1]);

    myRoot.getChildren().add(newLine);
  }

  /**
   * Rotates TurtleView orientation.
   *
   * @param updatedTurtle already updated turtle object
   * @param currTurtleView needs to be updated view
   */
  private void rotateTurtleView(Turtle updatedTurtle, ImageView currTurtleView) {
    currTurtleView.setRotate(updatedTurtle.getOrientation());
  }

  /**
   * Moves TurtleView.
   *
   * @param updatedTurtle already updated turtle object
   * @param currTurtleView needs to be updated view
   */
  private void moveTurtleView(Turtle updatedTurtle, ImageView currTurtleView) {
    currTurtleView.setX(updatedTurtle.getLocation()[0]);
    currTurtleView.setY(updatedTurtle.getLocation()[1]);
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
