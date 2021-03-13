package slogo.frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelpButtonMaker extends ButtonMaker {
    private static final int HELP_HEIGHT = 750;
    private static final int HELP_WIDTH = 600;
    Group root = new Group();


    public HelpButtonMaker(String myLabel, int x, int y, Group myRoot) {
        super(myLabel, x, y, myRoot);
    }

    @Override
    EventHandler<ActionEvent> createHandler() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setScene(makeHelpScene());
                stage.setTitle("Help");
                stage.show();
            }
        };
    }
    private Scene makeHelpScene() {
        Scene scene = new Scene(root, HELP_WIDTH, HELP_HEIGHT);
        //scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
        return scene;
    }

}
