package slogo.frontend;

import java.beans.PropertyChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slogo.Observable;

/**
 * Creates basic window that can be extended.
 * TODO assumptions, dependencies, example of use
 *
 * @author Jessica Yang
 */
public class ViewMaker extends Observable<Object> {

  private final Stage viewStage = new Stage();
  private final BorderPane viewRoot = new BorderPane();
  private final Scene viewScene;
  public static final String DEFAULT_RESOURCE_PACKAGE = "slogo.frontend.resources.";
  public static final String DEFAULT_RESOURCE_FOLDER =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/" );
  public final String STYLE = "styles/";
  public static String styleSheet = "Default.css";

  private PropertyChangeListener cssListener;

  /**
   * Constructor for ViewMaker abstract class. Assumes setUpRoot is implemented in child classes.
   *
   * @param sizeX x dimension of window
   * @param sizeY y dimension of window
   * @param title string title of window
   */
  public ViewMaker(double sizeX, double sizeY, String title) {
    viewScene = new Scene(viewRoot, sizeX, sizeY);
    updateStyleSheet();
    viewStage.setTitle(title);
    viewStage.setScene(viewScene);
    cssListener = evt -> {
      if (evt.getPropertyName().equals("css")) {
        styleSheet = (String) evt.getNewValue();
        updateStyleSheet();
      }
    };
  }

  private void updateStyleSheet() {
    viewScene.getStylesheets().clear();
    viewScene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLE
        + styleSheet).toExternalForm());
  }
  /**
   * Retrieves listener used by ViewMaker.
   *
   * @return cssListener
   */
  public PropertyChangeListener getListener() {
    return cssListener;
  }

  /**
   * Used by child-classes to access class root.
   *
   * @return viewRoot to be added to
   */
  protected BorderPane getRoot() {
    return viewRoot;
  }

  /**
   * Used by child-classes to access class stage.
   *
   * @return viewStage to be added to
   */
  protected Stage getStage() {
    return viewStage;
  }

  /**
   * Text object maker and formatter for child classes.
   *
   * @param xCoordinate x location
   * @param yCoordinate y location
   * @param text content of text
   * @param id text id
   * @return newText Text object
   */
  protected Text makeText(double xCoordinate, double yCoordinate, String text, String id) {
    Text newText = new Text(xCoordinate, yCoordinate, text);
    newText.setFill(Color.BLACK);
    newText.setId(id);

    return newText;
  }

  /**
   * Button object maker and formatter for child classes.
   *
   * @param xCoordinate x location
   * @param yCoordinate y location
   * @param text content of text
   * @return newButton Button object
   */
  protected Button makeButton(double xCoordinate, double yCoordinate, String text) {
    Button newButton = new Button(text);
    newButton.relocate(xCoordinate, yCoordinate);
    newButton.setId(text.replaceAll(" ", ""));
    return newButton;
  }

  /**
   * Displays window.
   */
  public void showView() {
    viewStage.show();
  }

  /**
   * Updates the css used for styling the view.
   *
   * @param style filename of new .css
   */
  public void changeStyle(String style) {
    notifyListeners("css", styleSheet, (style + ".css"));
    styleSheet = style + ".css";
    updateStyleSheet();
  }

}
