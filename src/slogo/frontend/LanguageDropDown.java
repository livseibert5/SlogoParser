package slogo.frontend;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import slogo.Observable;
import slogo.controller.Controller;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LanguageDropDown extends Observable<Object> {

    public LanguageDropDown(Group root, Controller controller) {
        ResourceBundle myLanguages = ResourceBundle
                .getBundle("resources.languages.LangaugeOptions");
        ArrayList<String> list = new ArrayList<>(myLanguages.keySet());
        ComboBox<String> languages = new ComboBox<String>(FXCollections.observableList(list));
        languages.setValue("English" );
        languages.setId("language");
        root.getChildren().add(languages);
        languages.setOnAction(event -> controller.setLanguage(languages.getValue()));
    }
}
