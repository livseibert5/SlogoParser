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
 * Updates the front-end representation of a turtle. Assumes turtle image is set to the rightward
 * direction.
 *
 * @author Jessica Yang
 */
public class TurtleDisplay implements PropertyChangeListener {

  private static final String DEFAULT_IMAGE_PATH = "/" + (TurtleDisplay.class.getPackageName()
      + ".resources.images.").replace('.', '/');
  private static final String IMAGE_FILE = DEFAULT_IMAGE_PATH + "temp_turtle.jpg";

  private final Map<Integer, Turtle> turtleMap = new HashMap<>();
  private final Map<Integer, ImageView> turtleViewMap = new HashMap<>();
  private String DEFAULT_IMAGE_PATH2 = "/" + (TurtleDisplay.class.getPackageName() + ".resources.images.").replace('.', '/');
  private String IMAGE_FILE2 = DEFAULT_IMAGE_PATH2 + "temp_turtle.jpg";
  private String USER_FILE = DEFAULT_IMAGE_PATH2 + "UserImage.jpg";
  private ImageView turtleView;

  private static final double dimensionSize = 50;
  private static final double TURTLE_OFFSET = dimensionSize / 2;
  private static final double X_CENTER_OFFSET = 1000 - TURTLE_OFFSET;
  private static final double Y_CENTER_OFFSET = 375 - TURTLE_OFFSET;

  private final Group myRoot;
  private Color lineColor;

  /**
   * Constructor for TurtleDisplay. Takes in map of turtles from Controller.
   *
   * @param turtle from backend
   */
  public TurtleDisplay(Turtle turtle, Group root) {
    turtleMap.put(1, turtle);
    myRoot = root;
    updateImageMap();
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
    turtleView = new ImageView();
    turtleView.setImage(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(IMAGE_FILE))));

    turtleView.setX(toAddTurtle.getXCoordinate() + X_CENTER_OFFSET);
    turtleView.setY(-1 * toAddTurtle.getYCoordinate() + Y_CENTER_OFFSET);

    turtleView.setFitHeight(dimensionSize);
    turtleView.setFitWidth(dimensionSize);

    turtleView.setRotate(toAddTurtle.getOrientation() * -1);
    turtleView.setId("turtle" + id);
    turtleViewMap.put(id, turtleView);
    myRoot.getChildren().add(turtleView);
    turtleView.toFront();
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

    rotateTurtleView(updatedTurtle.getOrientation() * -1, currTurtleView);
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
    newLine.setStartX(currTurtleView.getX() + TURTLE_OFFSET);
    newLine.setStartY(currTurtleView.getY() + TURTLE_OFFSET);
    newLine.setEndX(newLocation[0] + X_CENTER_OFFSET + TURTLE_OFFSET);
    newLine.setEndY(-1 * newLocation[1] + Y_CENTER_OFFSET + TURTLE_OFFSET);

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
    currTurtleView.setX(newLocation[0] + X_CENTER_OFFSET);
    currTurtleView.setY(-1 * newLocation[1] + Y_CENTER_OFFSET);
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
  public void updateImageView() {
    turtleView.setImage(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("src/slogo/frontend/resources/images/UserImage.jpg"))));
  }
}
