package slogo.frontend;

import java.beans.PropertyChangeListener;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.ResourceBundle;
import slogo.Observable;

public class SceneComponents extends Observable {

  private final Group root;
  private TextArea commandLine;
  private static final int DEFAULT_HEIGHT = 750;
  private static final int DEFAULT_WIDTH = 1350;
  private static final int WINDOW_SIZE = 600;
  private static final int DEFAULT_BORDER = 50;
  public static final String DEFAULT_RESOURCE_PACKAGE = "slogo.frontend.resources.";
  private final ResourceBundle myResources = ResourceBundle
      .getBundle(DEFAULT_RESOURCE_PACKAGE + "text" );
  private final Rectangle turtleBox = new Rectangle(WINDOW_SIZE, WINDOW_SIZE, Color.WHITE);

  private Color oldLineColor = Color.BLACK;

  public SceneComponents(Group myRoot, List<PropertyChangeListener> listeners) {
    this.root = myRoot;
    addAllListeners(listeners);
  }

  private void addAllListeners(List<PropertyChangeListener> listeners) {
      for (PropertyChangeListener l : listeners) {
          addListener(l);
      }
  }


}

