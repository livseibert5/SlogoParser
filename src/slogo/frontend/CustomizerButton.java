package slogo.frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;

public class CustomizerButton extends ButtonMaker {
    public CustomizerButton(String myLabel, int x, int y, Group myRoot, EventHandler<ActionEvent> myHandler) {
        super(myLabel, x, y, myRoot, myHandler);
    }

    @Override
    EventHandler<ActionEvent> createHandler() {
        return null;
    }
}
