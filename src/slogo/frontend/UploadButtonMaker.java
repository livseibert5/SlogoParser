package slogo.frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class UploadButtonMaker extends ButtonMaker{
    private final Stage stage = new Stage();

    public UploadButtonMaker(String myLabel, int x, int y, Group myRoot) {
        super(myLabel, x, y, myRoot);
    }

    @Override
    EventHandler<ActionEvent> createHandler() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Upload Turtle Image");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
                File file = fileChooser.showOpenDialog(stage);
               if (file != null) {
                   boolean isMoved = file.renameTo(new File("src/slogo/frontend/resources/images/UserImage.jpg"));
                   System.out.println(isMoved);
               }
            }
        };
    }
}
