package slogo.frontend;


import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import slogo.model.Turtle;
import slogo.model.handlers.TurtleHandler;

/**
 * View for current state of a turtle.
 *
 * @author Jessica Yang
 */
public class TurtleDetailsView extends ViewMaker {

  private final TurtleHandler turtleHandler;
  private ComboBox<Integer> turtleIdComboBox;
  private ObservableList<Integer> allTurtleIds;

  private List<PropertyChangeListener> allListeners;
  private PropertyChangeListener turtleXCoordinateListener;
  private PropertyChangeListener turtleYCoordinateListener;
  private PropertyChangeListener turtleOrientationListener;
  private PropertyChangeListener penDownListener;
  private PropertyChangeListener addTurtleListener;

  private Text xLocation;
  private Text yLocation;
  private Text orientation;
  private Text penDown;


  /**
   * Constructor for TurtleDetailsView class.
   *
   * @param sizeX x dimension of window
   * @param sizeY y dimension of window
   */
  public TurtleDetailsView(int sizeX, int sizeY, TurtleHandler handler) {
    super(sizeX, sizeY, "Turtle Details");
    turtleHandler = handler;
    allListeners = new ArrayList<>();

    allTurtleIds = FXCollections.observableList(turtleHandler.getAllIds());
    setUpComboBox();
    setUpListeners();
    setUpRoot(getRoot(), sizeX, sizeY);
  }

  private void updateAllTurtleIds() {
    for (int id : turtleHandler.getAllIds()) {
      if (!allTurtleIds.contains(id)) {
        allTurtleIds.add(id);
      }
    }

  }

  private void setUpComboBox() {
    turtleIdComboBox = new ComboBox<>(allTurtleIds);
    turtleIdComboBox.setId("turtleIds");
    turtleIdComboBox.setOnAction(event -> {
      updateListeners(turtleIdComboBox.getValue());
      initializeTextValues(turtleIdComboBox.getValue());
    });
  }

  private void updateListeners(int id) {
    turtleHandler.getTurtle(id).addMultipleListeners(allListeners);
  }

  private void initializeTextValues(int id) {
    xLocation.setText(Double.toString(turtleHandler.getTurtle(id).getXCoordinate()));
    yLocation.setText(Double.toString(turtleHandler.getTurtle(id).getYCoordinate()));
    orientation.setText(Double.toString(turtleHandler.getTurtle(id).getOrientation()));
    penDown.setText(Boolean.toString(turtleHandler.getTurtle(id).penIsDown()));
  }

  private void setUpListeners() {
    turtleXCoordinateListener = evt -> {
      if (evt.getPropertyName().equals("xLocation")) {
        xLocation.setText(evt.getNewValue().toString());
      }
    };
    allListeners.add(turtleOrientationListener);

    turtleYCoordinateListener = evt -> {
      if (evt.getPropertyName().equals("yLocation")) {
        yLocation.setText(evt.getNewValue().toString());
      }
    };
    allListeners.add(turtleYCoordinateListener);

    turtleOrientationListener = evt -> {
      if (evt.getPropertyName().equals("orientation")) {
        orientation.setText(evt.getNewValue().toString());
      }
    };
    allListeners.add(turtleOrientationListener);

    penDownListener = evt -> {
      if (evt.getPropertyName().equals("penDown")) {
        penDown.setText(evt.getNewValue().toString());
      }
    };
    allListeners.add(penDownListener);

    addTurtleListener = evt -> {
      if (evt.getPropertyName().equals("addTurtle")) {
        updateAllTurtleIds();
      }
    };
    turtleHandler.addListener(addTurtleListener);
  }

  /**
   * Adds turtle details to root.
   *
   * @param myRoot to be added to
   * @param sizeX width of the window
   * @param sizeY height of the window
   */
  private void setUpRoot(Group myRoot, double sizeX, double sizeY) {
    //pencolor, location, orientation, up/down, thickness?
    myRoot.getChildren().add(turtleIdComboBox);

    myRoot.getChildren().add(makeText(sizeX / 4, (sizeY / 4) - 20, "x",
        "xLocationLabel"));
    xLocation = makeText(sizeX / 4, sizeY / 4, null,"xLocation");
    myRoot.getChildren().add(xLocation);

    myRoot.getChildren().add(makeText(sizeX * (3 / 4.0), (sizeY / 4) - 20,
        "y", "yLocationLabel"));
    yLocation = makeText(sizeX * (3 / 4.0), sizeY / 4, null,
        "yLocation");
    myRoot.getChildren().add(yLocation);

    myRoot.getChildren().add(makeText(sizeX / 4, (sizeY / 2) - 20,
        "orientation", "orientationLabel"));
    orientation = makeText(sizeX / 4, sizeY / 2, null,"orientation");
    myRoot.getChildren().add(orientation);

    myRoot.getChildren().add(makeText(sizeX / 4, (sizeY * (3.0 / 4)) - 20,
        "penDown", "penDown"));
    penDown = makeText(sizeX / 4, sizeY * (3.0 / 4), null,"penDown");
    myRoot.getChildren().add(penDown);
  }

  @Override
  protected void setUpRoot(BorderPane myRoot, double sizeX, double sizeY) {

  }
}
