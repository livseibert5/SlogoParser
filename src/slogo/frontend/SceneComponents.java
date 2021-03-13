package slogo.frontend;

import java.beans.PropertyChangeListener;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
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
  private final ResourceBundle myLanguages = ResourceBundle
      .getBundle("resources.languages.LangaugeOptions" );
  private final Rectangle turtleBox = new Rectangle(WINDOW_SIZE, WINDOW_SIZE, Color.WHITE);

  private Color oldLineColor = Color.BLACK;

  public SceneComponents(Group myRoot, List<PropertyChangeListener> listeners) {
    this.root = myRoot;
    addEverything();
    addAllListeners(listeners);
  }

  private void addAllListeners(List<PropertyChangeListener> listeners) {
      for (PropertyChangeListener l : listeners) {
          addListener(l);
      }
  }

  public void addEverything() {
    addTurtleWindow();
    makeCommandField();
    makeVariableView();
    makeUserCommandView();
    addColorPickers();
    makeLanguageDropDown();
  }

  private void makeCommandField() {
    commandLine = new TextArea();
    commandLine.setPromptText("Type Commands Here" );
    commandLine.setPrefHeight(WINDOW_SIZE / 2);
    commandLine.setPrefWidth(WINDOW_SIZE);
    commandLine.relocate(DEFAULT_BORDER, DEFAULT_HEIGHT - commandLine.getPrefHeight()
        - 2 * DEFAULT_BORDER); //change this to avoid "magic numbers
    root.getChildren().add(commandLine);
  }

  public String getTextInput() {
    return commandLine.getText();
  }

  private void makeColorPicker(ColorPicker colorPicker, double x, double y, EventHandler event) {
    colorPicker.setOnAction(event);
    colorPicker.relocate(x, y);
  }

  private void addColorPickers() {
    double x = turtleBox.getX() + DEFAULT_BORDER + turtleBox.getWidth() / 2;
    Text backgroundTitle = new Text(x, DEFAULT_BORDER / 3, "Select Background Color:" );
    backgroundTitle.setId("colorlabel");
    //ColorPicker background = makeColorPicker(turtleBox, x, DEFAULT_BORDER / 2); //update this location
    final ColorPicker pen = new ColorPicker();
    makeColorPicker(pen,0, 200, new EventHandler() {
        @Override
        public void handle(Event event) {
            notifyListeners("LINECOLOR", oldLineColor, pen.getValue());
            oldLineColor = pen.getValue();
        }
    });
    root.getChildren().add(pen);
  }

  private void addTurtleWindow() {
    turtleBox.setFill(Color.WHITE);
    turtleBox.setStroke(Color.BLACK);
    turtleBox.setStrokeWidth(2);
    turtleBox.setX(DEFAULT_WIDTH - turtleBox.getWidth() - DEFAULT_BORDER);
    turtleBox.setY((DEFAULT_HEIGHT - turtleBox.getHeight()) / 2);
    root.getChildren().add(turtleBox);
  }

  private Scene makeHelpScene() {
    Group root = new Group();
    Scene scene = new Scene(root, WINDOW_SIZE, WINDOW_SIZE);
    //scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
    return scene;
  }

  private void makeVariableView() {
    TableView table = new TableView();
    TableColumn name = new TableColumn("Name" );
    TableColumn type = new TableColumn("Type" );
    TableColumn value = new TableColumn("Value" );
    table.getColumns().addAll(name, type, value);
    table.setPrefSize((WINDOW_SIZE - DEFAULT_BORDER) / 2, (WINDOW_SIZE - DEFAULT_BORDER) / 2);
    table.relocate(DEFAULT_BORDER, DEFAULT_BORDER);
    root.getChildren().add(table);
  }

  private void makeUserCommandView() {
    TableView table = new TableView();
    TableColumn command = new TableColumn("Command" );
    TableColumn definition = new TableColumn("Definition" );
    table.getColumns().addAll(command, definition);
    table.setPrefSize((WINDOW_SIZE - DEFAULT_BORDER) / 2, (WINDOW_SIZE - DEFAULT_BORDER) / 2);
    table.relocate(DEFAULT_BORDER * 2 + table.getPrefWidth(), DEFAULT_BORDER);
    root.getChildren().add(table);
  }

  private void makeLanguageDropDown() {
    ArrayList<String> list = new ArrayList<>(myLanguages.keySet());
    ComboBox<String> languages = new ComboBox<String>(FXCollections.observableList(list));
    languages.setValue("English" );
    root.getChildren().add(languages);
  }

}

