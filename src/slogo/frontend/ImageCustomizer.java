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
    private List<ImageView> images;
    private List<Rectangle> colors;
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
        HBox palette = new HBox(10);
        images = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            try {
                ImageView option = new ImageView(new Image(new FileInputStream("src/slogo/frontend/resources/images/UserImage" + i + ".jpg")));
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
    private void addBackgroundColor() {
        ColorPickerMaker backgroundColor = new ColorPickerMaker(column,  "Background");
        backgroundColor.setHandler(event -> myWindow.setColor(backgroundColor.getNewColor()));
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
