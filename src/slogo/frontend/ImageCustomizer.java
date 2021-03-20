package slogo.frontend;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ImageCustomizer extends ViewMaker{
    private Stage stage = new Stage();
    private VBox column;
    private int imageNumber = 1;
    private static final double IMAGE_SIZE = 50;
    private final TurtleDisplay myTurtleDisplay;
    private final TurtleWindow myWindow;
    private ImageView[] images;
    private Rectangle[] colors;
    /**
     * Constructor for ViewMaker abstract class. Assumes setUpRoot is implemented in child classes.
     *
     * @param sizeX x dimension of window
     * @param sizeY y dimension of window
     * @param title string title of window
     */
    public ImageCustomizer(double sizeX, double sizeY, String title, TurtleDisplay turtles, TurtleWindow window) {
        super(sizeX, sizeY, title);
        myTurtleDisplay = turtles;
        myWindow = window;
    }

    @Override
    protected void setUpRoot(BorderPane myRoot, double sizeX, double sizeY) {
        column = new VBox(5);
        UploadButtonMaker uploadButton = new UploadButtonMaker("Upload Image", column, event -> uploadEvent());
        column.getChildren().addAll(imagePalette(), penColorPalette());
        addBackgroundColor();
        myRoot.setTop(column);
        column.setAlignment(Pos.CENTER);
    }

    private void uploadEvent() { //need to edit this a lot
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Turtle Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            boolean isMoved = file.renameTo(new File("src/slogo/frontend/resources/images/UserImage" + imageNumber + ".jpg"));
        }
    }
    private HBox imagePalette() {
        images = new ImageView[10];
        HBox palette = new HBox(10);
        for (int i = 0; i < 10; i++) {
            try {
                String path = "src/slogo/frontend/resources/images/" + i + ".jpg";
                ImageView option = new ImageView(new Image(new FileInputStream(path)));
                option.setFitHeight(IMAGE_SIZE);
                option.setFitWidth(IMAGE_SIZE);
                palette.getChildren().add(option);
                option.setOnMouseClicked(event -> myTurtleDisplay.updateImageView(path));
                images[i] = option;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return palette;
    }
    private void addBackgroundColor() {
        ColorPickerMaker backgroundColor = new ColorPickerMaker(column,  "Background");
        backgroundColor.setHandler(event -> myWindow.setColor(backgroundColor.getNewColor()));
    }
    private HBox penColorPalette() {
        colors = new Rectangle[10];
        HBox palette = new HBox(10);
        for (int i = 0; i < 10; i++) {
            Rectangle option = new Rectangle(IMAGE_SIZE, IMAGE_SIZE, Color.RED);
            palette.getChildren().add(option);
            colors[i] =option;
        }
        return palette;
    }
}
