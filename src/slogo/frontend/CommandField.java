package slogo.frontend;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandField {
    private Group root;
    private TextArea commandLine;
    private ListView<String> pastCommands = new ListView<>();
    private List<String> commands = new ArrayList<>();
    private double size;
    private double border;
    private double height;

    public CommandField(Group root, double WINDOW_SIZE, double DEFAULT_BORDER, double DEFAULT_HEIGHT) {
        this.root = root;
        this.size = WINDOW_SIZE;
        this.border = DEFAULT_BORDER;
        this.height = DEFAULT_HEIGHT;
        commandLine = new TextArea();
        commandLine.setPromptText("Type Commands Here" );
        commandLine.setPrefHeight(WINDOW_SIZE / 2);
        commandLine.setPrefWidth(WINDOW_SIZE/2 - DEFAULT_BORDER/4);
        commandLine.relocate(DEFAULT_BORDER, DEFAULT_HEIGHT - commandLine.getPrefHeight()
                - 2 * DEFAULT_BORDER);
        commandLine.setId("commandLine");
        root.getChildren().add(commandLine);
        showPastCommands();
    }

    public void printReturnValue(int value) {
        System.out.println(value);
        commandLine.setText(String.valueOf(value));
        System.out.println(commandLine.getText());
        commandLine.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                commandLine.clear();
            }
        });
    }
    private void showPastCommands() {
        pastCommands.setPrefSize(size/2 - border/4, size/ 2);
        pastCommands.relocate(border*1.5 + pastCommands.getPrefWidth(), height - pastCommands.getPrefHeight()
                - 2 * border);
        root.getChildren().add(pastCommands);

    }

    public String getTextInput() {
        return commandLine.getText();
    }
    public void clearTextInput() {
        commands.add(commandLine.getText());
        pastCommands.setItems(FXCollections.observableList(commands));
        commandLine.clear();
    }
}
