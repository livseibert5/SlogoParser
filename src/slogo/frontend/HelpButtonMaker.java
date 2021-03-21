package slogo.frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HelpButtonMaker extends ButtonMaker {
    private static final int HELP_HEIGHT = 750;
    private static final int HELP_WIDTH = 600;
    public static final String DEFAULT_RESOURCE_PACKAGE = "slogo.frontend.resources.";
    public static final String DEFAULT_RESOURCE_FOLDER =
            "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/" );
    public static final String STYLESHEET = "default.css";
    private Group helpRoot = new Group();

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
                results.add(file.getName()); //.substring(0, file.getName().lastIndexOf('.')));
            }
        }
        Collections.sort(results);
        return results;
    }

    private void addAllButtons(List<String> list) {
        int column = 0;
        int row = 0;
        for (int i = 0; i < list.size(); i++) {
            Button link = makeButton(list.get(i), column * 150, row * 50);
            int finalI = i;
            link.setOnAction(event -> {
                Group PopUpRoot = new Group();
                Stage stage = new Stage();
                setText(PopUpRoot, list.get(finalI));
                SceneMaker helpScene = new SceneMaker(PopUpRoot, stage, HELP_WIDTH - 100, HELP_HEIGHT - 100);
                stage.setTitle(list.get(finalI));
            });
            helpRoot.getChildren().add(link);
            column++;
            if (column == 4) {
                column = 0;
                row++;
            }
        }
    }
    private void setText(Group root, String file) {
        TextArea text = new TextArea();
        text.setPrefSize(HELP_WIDTH - 100, HELP_HEIGHT - 100);
        text.setEditable(false);
        text.setWrapText(true);
        System.out.println(file);
        List<String> lines = new ArrayList<>();
        String read;
        try {
            FileReader fr = new FileReader("src/slogo/frontend/resources/reference/" + file);//read 'file'
            BufferedReader br = new BufferedReader(fr);//read 'file'
            while ((read = br.readLine()) != null) {
                lines.add(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            text.appendText(line + "\n");
        }
        root.getChildren().add(text);
    }

}
