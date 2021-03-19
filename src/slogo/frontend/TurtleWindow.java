package slogo.frontend;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TurtleWindow {
    private Rectangle turtleBox;

    public TurtleWindow(Group root, double WINDOW_SIZE, double DEFAULT_BORDER, double DEFAULT_WIDTH, double DEFAULT_HEIGHT) {
        turtleBox = new Rectangle(WINDOW_SIZE, WINDOW_SIZE, Color.WHITE);
        turtleBox.setFill(Color.WHITE);
        turtleBox.setStroke(Color.BLACK);
        turtleBox.setStrokeWidth(2);
        turtleBox.setX(DEFAULT_WIDTH - turtleBox.getWidth() - DEFAULT_BORDER);
        turtleBox.setY((DEFAULT_HEIGHT - turtleBox.getHeight()) / 2);
        turtleBox.setId("turtleBox");
        root.getChildren().add(turtleBox);
        turtleBox.toBack();
    }
    public void setColor(Color color) {
        turtleBox.setFill(color);
    }
}
