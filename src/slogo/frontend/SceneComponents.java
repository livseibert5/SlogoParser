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
  private final ResourceBundle myLanguages = ResourceBundle
      .getBundle("resources.languages.LangaugeOptions" );
  private final Rectangle turtleBox = new Rectangle(WINDOW_SIZE, WINDOW_SIZE, Color.WHITE);
  private ListView pastCommands = new ListView();
  private List<String> commands = new ArrayList<>();

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
    addColorPickers();
    showPastCommands();
    makeLanguageDropDown();
  }

  private void makeCommandField() {
    commandLine = new TextArea();
    commandLine.setPromptText("Type Commands Here" );
    commandLine.setPrefHeight(WINDOW_SIZE / 2);
    commandLine.setPrefWidth(WINDOW_SIZE/2 - DEFAULT_BORDER/4);
    commandLine.relocate(DEFAULT_BORDER, DEFAULT_HEIGHT - commandLine.getPrefHeight()
        - 2 * DEFAULT_BORDER); //change this to avoid "magic numbers
    commandLine.setId("commandLine");
    root.getChildren().add(commandLine);
  }

  public void printReturnValue(int value) {
    System.out.println(value);
    commandLine.setText(String.valueOf(value));
    System.out.println(commandLine.getText());
    commandLine.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        commandLine.clear();
      }
    });
  }

  private void showPastCommands() {
    pastCommands.setPrefSize(WINDOW_SIZE/2 - DEFAULT_BORDER/4, WINDOW_SIZE/ 2);
    pastCommands.relocate(DEFAULT_BORDER*1.5 + pastCommands.getPrefWidth(), DEFAULT_HEIGHT - pastCommands.getPrefHeight()
            - 2 * DEFAULT_BORDER);
    root.getChildren().add(pastCommands);

  }

  public String getTextInput() {
    return commandLine.getText();
  }
  public void clearTextInput() {
    commands.add(commandLine.getText());
    pastCommands.setItems(FXCollections.observableList(commands));
    commandLine.clear();
  }

  private void makeColorPicker(ColorPicker colorPicker, double x, double y, EventHandler event) {
    colorPicker.setOnAction(event);
    colorPicker.relocate(x, y);
  }

  private void addColorPickers() {
    double y = DEFAULT_BORDER / 3;
    double x = turtleBox.getX() + DEFAULT_BORDER + turtleBox.getWidth() / 2;
    Text backgroundTitle = new Text(x, y, "Select Background Color:" );
    backgroundTitle.setId("colorlabel");
    final ColorPicker background = new ColorPicker();
    makeColorPicker(background, x, DEFAULT_BORDER / 2, new EventHandler() {
      @Override
      public void handle(Event event) {
        turtleBox.setFill(background.getValue());
      }
    });
    background.setId("background");
    final ColorPicker pen = new ColorPicker();
    Text penTitle = new Text(DEFAULT_BORDER + turtleBox.getX(), y, "Select Pen Color:");
    penTitle.setId("colorlabel");
    makeColorPicker(pen,DEFAULT_BORDER + turtleBox.getX(), DEFAULT_BORDER / 2, new EventHandler() {
        @Override
        public void handle(Event event) {
            notifyListeners("lineColor", oldLineColor, pen.getValue());
            oldLineColor = pen.getValue();
        }
    });
    pen.setId("pen");
    root.getChildren().addAll(backgroundTitle, background,penTitle, pen);
  }

  private void addTurtleWindow() {
    turtleBox.setFill(Color.WHITE);
    turtleBox.setStroke(Color.BLACK);
    turtleBox.setStrokeWidth(2);
    turtleBox.setX(DEFAULT_WIDTH - turtleBox.getWidth() - DEFAULT_BORDER);
    turtleBox.setY((DEFAULT_HEIGHT - turtleBox.getHeight()) / 2);
    turtleBox.setId("turtleBox");
    root.getChildren().add(turtleBox);
    turtleBox.toBack();
  }

  private Scene makeHelpScene() {
    Group root = new Group();
    Scene scene = new Scene(root, WINDOW_SIZE, WINDOW_SIZE);
    //scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
    return scene;
  }

  private void makeLanguageDropDown() {
    ArrayList<String> list = new ArrayList<>(myLanguages.keySet());
    ComboBox<String> languages = new ComboBox<String>(FXCollections.observableList(list));
    languages.setValue("English" );
    languages.setId("language");
    root.getChildren().add(languages);
  }

}

