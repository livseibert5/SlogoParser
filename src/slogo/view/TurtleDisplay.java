package slogo.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.model.Turtle;

/**
 * Updates the front-end representation of a turtle.
 */
public class TurtleDisplay {

  private final Map<Integer, Turtle> turtleMap;
  private Map<Integer, ImageView> turtleViewMap = new HashMap<>();
  private String IMAGE_FILE;

  private double dimensionSize;

  /**
   * Constructor for TurtleDisplay. Takes in map of turtles from Controller.
   *
   * @param turtles from backend
   */
  public TurtleDisplay(Map<Integer, Turtle> turtles) {
    turtleMap = turtles;
    updateImageMap();
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

  private void drawLine() {}
}
