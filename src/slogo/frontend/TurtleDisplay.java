package slogo.frontend;

import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import slogo.model.handlers.ColorHandler;
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
  private static final String IMAGE_FILE = DEFAULT_IMAGE_PATH + "0.jpg";
  private static final String USER_IMAGE_FILE = DEFAULT_IMAGE_PATH + "UserImage.jpg";

  private final ColorHandler colorHandler;
  private final TurtleHandler turtleHandler;
  private final Map<Integer, ImageView> turtleViewMap = new HashMap<>();

  private static final double dimensionSize = 50;
  private static final double TURTLE_OFFSET = dimensionSize / 2;
  private static final double X_CENTER_OFFSET = 1000 - TURTLE_OFFSET;
  private static final double Y_CENTER_OFFSET = 375 - TURTLE_OFFSET;
  private static final double ROTATION_OFFSET = 90;

  private final Group myRoot;

  List<PropertyChangeListener> allListeners = new ArrayList<>();
  private PropertyChangeListener turtleChangeListener;
  private PropertyChangeListener addTurtleListener;

  /**
   * Constructor for TurtleDisplay. Takes in map of turtles from Controller.
   *
   * @param root Group from Window
   * @param turtleHandler from controller
   * @param colorHandler from controller
   */
  public TurtleDisplay(Group root, TurtleHandler turtleHandler, ColorHandler colorHandler) {
    this.turtleHandler = turtleHandler;
    this.colorHandler = colorHandler;
    myRoot = root;
    setUpListeners();
    updateImageMap();
  }

  private void setUpListeners() {
    addTurtleListener = evt -> {
      if (evt.getPropertyName().equals("addTurtle")) {
        updateImageMap();
      }
    };
    allListeners.add(addTurtleListener);

    turtleChangeListener = evt -> {
      if (evt.getPropertyName().equals("xLocation")
          || evt.getPropertyName().equals("yLocation")
          || evt.getPropertyName().equals("orientation")) {
        updateTurtleView();
      }
    };
    allListeners.add(turtleChangeListener);
  }

  /**
   * Adds imageview equivalent for each turtle in turtleMap. TODO use observer
   */
  private void updateImageMap() {
    for (int id : turtleHandler.getAllIds()) {
      if (turtleViewMap.get(id) == null) {
        addTurtleView(id);
        turtleHandler.getTurtle(id).addListener(turtleChangeListener);
      }
    }

    updateTurtleView();
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

    turtleView.setRotate(toAddTurtle.getOrientation() * -1 + ROTATION_OFFSET);
    turtleView.setId("turtle" + id);

    turtleViewMap.put(id, turtleView);
    myRoot.getChildren().add(turtleView);
    turtleView.toFront();
  }

  /**
   * Updates state of a given turtle. Assumes turtles in turtleMap have been updated.
   */
  private void updateTurtleView() {
    for (Turtle t : turtleHandler.getActiveTurtles()) {
      int id = turtleHandler.getTurtleId(t);
      ImageView currTurtleView = turtleViewMap.get(id);

      if (t.penIsDown()) {
        drawNewLine(t, currTurtleView);
      }

      rotateTurtleView(t.getOrientation() * -1, currTurtleView);
      moveTurtleView(t.getLocation(), currTurtleView);
      updateTurtleViewVisibility(t.isShowing(), currTurtleView);
    }
  }

  /**
   * Changes visibility of TurtleDetailsView.
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
   * @param currTurtle updated turtle
   * @param currTurtleView needs to be updated view
   */
  private void drawNewLine(Turtle currTurtle, ImageView currTurtleView) {
    Line newLine = new Line();
    Color lineColor = parsePenColor(currTurtle.getPenColor());
    double lineThickness = currTurtle.getPenThickness();

    newLine.setFill(lineColor);
    newLine.setStroke(lineColor);
    newLine.setStrokeWidth(lineThickness);
    newLine.setStartX(currTurtleView.getX() + TURTLE_OFFSET);
    newLine.setStartY(currTurtleView.getY() + TURTLE_OFFSET);
    newLine.setEndX(currTurtle.getXCoordinate() + X_CENTER_OFFSET + TURTLE_OFFSET);
    newLine.setEndY(-1 * currTurtle.getYCoordinate() + Y_CENTER_OFFSET + TURTLE_OFFSET);

    newLine.setId("line");
    myRoot.getChildren().add(newLine);
  }

  /**
   * Rotates TurtleDetailsView orientation.
   *
   * @param newOrientation boolean
   * @param currTurtleView needs to be updated view
   */
  private void rotateTurtleView(double newOrientation, ImageView currTurtleView) {
    currTurtleView.setRotate(newOrientation + ROTATION_OFFSET);
  }

  /**
   * Moves TurtleDetailsView.
   *
   * @param newLocation double array
   * @param currTurtleView needs to be updated view
   */
  private void moveTurtleView(double[] newLocation, ImageView currTurtleView) {
    currTurtleView.setX(newLocation[0] + X_CENTER_OFFSET);
    currTurtleView.setY(-1 * newLocation[1] + Y_CENTER_OFFSET);
  }

  /** Retrieves javafx Color object from slogo color. */
  private Color parsePenColor(double colorIndex) {
    slogo.model.Color penColor = colorHandler.getColor(colorIndex);
    return new Color(penColor.getR(), penColor.getG(), penColor.getB(), 1.0);
  }

  /**
   * Sets new pen line color.
   *
   * @param newColor color object
   */
  public void setLineColor(Color newColor) {
    //lineColor = newColor;
  }

  /**
   * Sets new pen line thickness.
   *
   * @param thickness of pen
   */
  public void setLineThickness(Double thickness) {
    //lineThickness = thickness;
  }

  /**
   * Returns list of PropertyChangeListener to update values.
   *
   * @return PropertyChangeListener lineColorListener
   */
  public List<PropertyChangeListener> getListeners() { ;
    return allListeners;
  }

  /**
   * Updates image file used for turtles in the box.
   */
  public void updateImageView(String path) {
    for (Integer i : turtleViewMap.keySet()) {
      try {
        turtleViewMap.get(i).setImage(new Image(new FileInputStream(path)));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
  }

}
