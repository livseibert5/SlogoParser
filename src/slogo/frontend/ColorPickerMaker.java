package slogo.frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import slogo.Observable;

public class ColorPickerMaker {
    ColorPicker colorPicker = new ColorPicker();
    VBox box = new VBox();

    public ColorPickerMaker(Group root, double x, double y, String label) {
        colorPicker.setId(label);
        Text title = new Text("Select " + label + " Color:");
        title.setId("colorlabel");
        box.getChildren().addAll(title, colorPicker);
        box.relocate(x,y);
        root.getChildren().add(box);
    }
    public void setHandler(EventHandler<ActionEvent> event) {
        colorPicker.setOnAction(event);
    }
    public Color getNewColor() {
        return colorPicker.getValue();
    }
}
