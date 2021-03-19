package slogo.frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.stage.Stage;

public class WorkspaceButtonMaker extends ButtonMaker {

    public WorkspaceButtonMaker(String myLabel, int x, int y, Group myRoot) {
        super(myLabel, x, y, myRoot);
    }

    @Override
    EventHandler<ActionEvent> createHandler() {
        Stage stage = new Stage();
        return event -> {WindowControl window = new WindowControl(stage);};
    }
}
