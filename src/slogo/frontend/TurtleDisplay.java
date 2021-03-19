package slogo.frontend;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import slogo.model.Turtle;
import slogo.model.handlers.TurtleHandler;

/**
 * Updates the front-end representation of a turtle. Assumes turtle image is set to the rightward
 * direction.
 *
 * @author Jessica Yang
 */
public class TurtleDisplay {

  private static final String DEFAULT_IMAGE_PATH = "/" + (TurtleDisplay.class.getPackageName()
      + ".resources.images.").replace('.', '/');
  private static final String IMAGE_FILE = DEFAULT_IMAGE_PATH + "temp_turtle.jpg";
  private static final String USER_IMAGE_FILE = DEFAULT_IMAGE_PATH + "UserImage.jpg";

  private final TurtleHandler turtleHandler;
  private final Map<Integer, ImageView> turtleViewMap = new HashMap<>();

  private static final double dimensionSize = 50;
  private static final double TURTLE_OFFSET = dimensionSize / 2;
  private static final double X_CENTER_OFFSET = 1000 - TURTLE_OFFSET;
  private static final double Y_CENTER_OFFSET = 375 - TURTLE_OFFSET;

  private final Group myRoot;
  private Color lineColor;

  private final PropertyChangeListener lineColorListener;
  private final PropertyChangeListener addTurtleListener;

  /**
   * Constructor for TurtleDisplay. Takes in map of turtles from Controller.
   *
   * @param handler from backend
   */
  public TurtleDisplay(TurtleHandler handler, Group root) {
    turtleHandler = handler;
    myRoot = root;
    updateImageMap();
    lineColor = Color.BLACK;
    lineColorListener = evt -> {
      if (evt.getPropertyName().equals("lineColor")) {
        setLineColor((Color) evt.getNewValue());
      }
    };
    addTurtleListener = evt -> {
      if (evt.getPropertyName().equals("addTurtle")) {
        updateImageMap();
      }
    };
  }

  /**
   * Adds imageview equivalent for each turtle in turtleMap. TODO use observer
   */
  private void updateImageMap() {
    for (Turtle t : turtleHandler.getActiveTurtles()) {
      int id = turtleHandler.getTurtleId(t);
      if (turtleViewMap.get(id) == null) {
        addTurtleView(id);
      }
    }
  }

  /**
   * Instantiates imageview objects for turtles in turtleViewMap.
   */
  private void addTurtleView(int id) {
    Turtle toAddTurtle = turtleHandler.getTurtle(id);
    ImageView turtleView = new ImageView();
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
    Turtle updatedTurtle = turtleHandler.getTurtle(id);
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
    newLine.setStrokeWidth(5);
    newLine.setStartX(currTurtleView.getX() + TURTLE_OFFSET);
    newLine.setStartY(currTurtleView.getY() + TURTLE_OFFSET);
    newLine.setEndX(newLocation[0] + X_CENTER_OFFSET + TURTLE_OFFSET);
    newLine.setEndY(-1 * newLocation[1] + Y_CENTER_OFFSET + TURTLE_OFFSET);

    newLine.setId("line");
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
   * Updates lineColor.
   *
   * @param newColor updated color
   */
  private void setLineColor(Color newColor) {
    lineColor = newColor;
  }

  /**
   * Returns PropertyChangeListener to update line color from GUI.
   *
   * @return PropertyChangeListener lineColorListener
   */
  public List<PropertyChangeListener> getListeners() {
    List<PropertyChangeListener> allListeners = new ArrayList<>();
    allListeners.add(lineColorListener);
    allListeners.add(addTurtleListener);
    return allListeners;
  }

  /**
   * Updates image file used for turtles in the box.
   *
   */

  public void updateImageView(int imageNumber) {
    for (Integer i : turtleViewMap.keySet()) {
      String image = "src/slogo/frontend/resources/images/UserImage" + imageNumber + ".jpg";
      System.out.println(image);
      turtleViewMap.get(i).setImage(new Image(getClass().getResourceAsStream(image)));
    }
  }
}
