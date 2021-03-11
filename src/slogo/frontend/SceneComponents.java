package slogo.frontend;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class SceneComponents {
    private final Group root;
    private final Stage stage;
    private TextArea commandLine;
    private static final int DEFAULT_HEIGHT = 750;
    private static final int DEFAULT_WIDTH = 1350;
    private static final int WINDOW_SIZE = 600;
    private static final int DEFAULT_BORDER = 50;
    public static final String DEFAULT_RESOURCE_PACKAGE = "slogo.frontend.resources.";
    private final ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "text");
    private final Rectangle turtleBox = new Rectangle(WINDOW_SIZE, WINDOW_SIZE, Color.WHITE);

    public SceneComponents(Group myRoot, Stage myStage) {
        this.root = myRoot;
        this.stage = myStage;
    }
    public void addEverything() {
        addTurtleWindow();
        makeCommandField();
        addButtons();
        addColorPickers();
    }

    //terminal (textfield)
    private void makeCommandField() {
        commandLine = new TextArea("Type Commands Here");
        //specifics about the Text Area
        commandLine.setPrefHeight(WINDOW_SIZE/2);
        commandLine.setPrefWidth(WINDOW_SIZE);
        commandLine.relocate(DEFAULT_BORDER,DEFAULT_HEIGHT - commandLine.getPrefHeight() - 2*DEFAULT_BORDER); //change this to avoid "magic numbers
        root.getChildren().add(commandLine);
    }
    //help popup, error popup

    private Button makeButton(String name, double x, double y, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        String label = myResources.getString(name);
        result.setId(label);
        result.setText(label);
        result.setOnAction(handler);
        result.relocate(x, y);
        return result;
    }

    private void addButtons() {
        Button help = makeButton("Help", turtleBox.getX() + turtleBox.getWidth()/2, DEFAULT_HEIGHT - DEFAULT_BORDER, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setScene(makeHelpScene());
                stage.setTitle("Help");
                stage.show();
            }
        });
        Button enter = makeButton("Enter", WINDOW_SIZE/2, DEFAULT_HEIGHT - DEFAULT_BORDER, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        root.getChildren().addAll(help, enter);
    }

    //pen color dropdown, background color dropdown
    private ColorPicker makeColorPicker(Shape shape, double x, double y) {
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                shape.setFill(colorPicker.getValue());
            }
        });
        colorPicker.relocate(x, y);
        return colorPicker;
    }
    private void addColorPickers() {
        double x = turtleBox.getX() + + DEFAULT_BORDER + turtleBox.getWidth()/2;
        Text backgroundTitle = new Text(x, DEFAULT_BORDER/3, "Select Background Color:");
        backgroundTitle.setId("colorlabel");
        ColorPicker background = makeColorPicker(turtleBox, x, DEFAULT_BORDER/2); //update this location
        //ColorPicker pen = makeColorPicker();
        root.getChildren().addAll(backgroundTitle, background);
    }

    private void addTurtleWindow() {
        turtleBox.setFill(Color.WHITE);
        turtleBox.setStroke(Color.BLACK);
        turtleBox.setStrokeWidth(2);
        turtleBox.setX(DEFAULT_WIDTH - turtleBox.getWidth() - DEFAULT_BORDER);
        turtleBox.setY((DEFAULT_HEIGHT - turtleBox.getHeight())/2);
        root.getChildren().add(turtleBox);
    }
    private Scene makeHelpScene() {
        Group root = new Group();
        Scene scene = new Scene(root, WINDOW_SIZE, WINDOW_SIZE);
        //scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
        return scene;
    }
    private TableView

}

