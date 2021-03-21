package slogo.frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
<<<<<<< HEAD
import javafx.stage.Stage;


public class UploadButtonMaker extends ButtonMaker{

    public UploadButtonMaker(String myLabel, int x, int y, Group myRoot, EventHandler<ActionEvent> eventHandler) {
        super(myLabel, x, y, myRoot, eventHandler);
=======
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class UploadButtonMaker extends ButtonMaker{
    public UploadButtonMaker(String myLabel, Pane myBox, EventHandler<ActionEvent> myHandler) {
        super(myLabel, myBox, myHandler);
>>>>>>> mal115
    }

    @Override
    EventHandler<ActionEvent> createHandler() {
        return null;
    }
}
