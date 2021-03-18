package slogo.frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.stage.Stage;


public class UploadButtonMaker extends ButtonMaker{

    public UploadButtonMaker(String myLabel, int x, int y, Group myRoot, EventHandler<ActionEvent> eventHandler) {
        super(myLabel, x, y, myRoot, eventHandler);
    }

    @Override
    EventHandler<ActionEvent> createHandler() {
        return null;
    }
}
