package slogo.frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;

abstract class ButtonMaker {
    private final Button button;
    String label;
    int x;
    int y;
    Group root;

    public ButtonMaker(String myLabel, int x, int y, Group myRoot) {
        label = myLabel;
        this.x = x;
        this.y = y;
        root = myRoot;
        button = makeButton(label, x, y);
        root.getChildren().add(button);
        button.setOnAction(createHandler());
    }

    public ButtonMaker(String myLabel, int x, int y, Group myRoot, EventHandler<ActionEvent> myHandler) {
        label = myLabel;
        this.x = x;
        this.y = y;
        root = myRoot;
        button = makeButton(label, x, y);
        root.getChildren().add(button);
        button.setOnAction(myHandler);
    }

    public Button makeButton(String name, double x, double y) {
        Button result = new Button();
        result.setId(name);
        result.setText(name);
        result.relocate(x, y);
        return result;
    }
    abstract EventHandler<ActionEvent> createHandler();
}
