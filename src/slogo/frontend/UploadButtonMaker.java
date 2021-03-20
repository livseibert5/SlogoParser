package slogo.frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class UploadButtonMaker extends ButtonMaker{
    public UploadButtonMaker(String myLabel, Pane myBox, EventHandler<ActionEvent> myHandler) {
        super(myLabel, myBox, myHandler);
    }

    @Override
    EventHandler<ActionEvent> createHandler() {
        return null;

    }
}
