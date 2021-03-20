package slogo.frontend;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private final VBox column = new VBox();
    private int imageNumber = 1;
    private static final double IMAGE_SIZE = 50;
    private final TurtleDisplay myTurtleDisplay;
    private List<ImageView> images;
    private List<Rectangle> colors;
    /**
     * Constructor for ViewMaker abstract class. Assumes setUpRoot is implemented in child classes.
     *
     * @param sizeX x dimension of window
     * @param sizeY y dimension of window
     * @param title string title of window
     */
    public ImageCustomizer(double sizeX, double sizeY, String title, TurtleDisplay turtles) {
        super(sizeX, sizeY, title);
        myTurtleDisplay = turtles;
    }

    @Override
    protected void setUpRoot(Group myRoot, double sizeX, double sizeY) {
        //column.getChildren().add(imagePalette());
        myRoot.getChildren().addAll(imagePalette(), penColorPalette());
        //column.setAlignment(Pos.TOP_CENTER);
    }
    private void imageUploader() {
        UploadButtonMaker uploadButton = new UploadButtonMaker("Upload Image", column, event -> uploadEvent());
    }
    private void uploadEvent() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Turtle Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            boolean isMoved = file.renameTo(new File("src/slogo/frontend/resources/images/UserImage" + imageNumber + ".jpg"));
            System.out.println(isMoved);
            myTurtleDisplay.updateImageView(imageNumber);
            imageNumber++;
        }
    }
    private HBox imagePalette() {
        HBox palette = new HBox(5);
        images = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            try {
                ImageView option = new ImageView(new Image(new FileInputStream("src/slogo/frontend/resources/images/temp_turtle.jpg")));
                option.setFitHeight(IMAGE_SIZE);
                option.setFitWidth(IMAGE_SIZE);
                palette.getChildren().add(option);
                images.add(option);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return palette;
    }
    private void BackgroundColor() {

    }
    private HBox penColorPalette() {
        HBox palette = new HBox(10);
        colors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Rectangle option = new Rectangle(IMAGE_SIZE, IMAGE_SIZE, Color.RED);
            palette.getChildren().add(option);
            colors.add(option);
        }
        return palette;
    }
}
