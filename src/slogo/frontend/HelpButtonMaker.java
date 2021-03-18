package slogo.frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelpButtonMaker extends ButtonMaker {
    private static final int HELP_HEIGHT = 1000;
    private static final int HELP_WIDTH = 600;
    public static final String DEFAULT_RESOURCE_PACKAGE = "slogo.frontend.resources.";
    public static final String DEFAULT_RESOURCE_FOLDER =
            "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/" );
    public static final String STYLESHEET = "default.css";
    Group helpRoot = new Group();


    public HelpButtonMaker(String myLabel, int x, int y, Group myRoot) {

        super(myLabel, x, y, myRoot);
        addAllButtons(createReferenceList());
    }

    @Override
    EventHandler<ActionEvent> createHandler() {
        return event -> {
            Stage stage = new Stage();
            stage.setScene(makeHelpScene());
            stage.setTitle("Help");
            stage.show();
        };
    }
    private Scene makeHelpScene() {
        Scene scene = new Scene(helpRoot, HELP_WIDTH, HELP_HEIGHT);
        scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
        return scene;
    }
    private List<String> createReferenceList() {
        List<String> results = new ArrayList<>();
        File[] files = new File("src/slogo/frontend/resources/reference").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        Collections.sort(results);
        return results;
    }

    private void addAllButtons(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            Button link = makeButton(list.get(i), 0, i * 40);
            link.setOnAction(event -> {

            });

            helpRoot.getChildren().add(link);
        }
    }

}
