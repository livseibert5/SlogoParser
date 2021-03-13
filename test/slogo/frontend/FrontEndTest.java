package slogo.frontend;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import util.DukeApplicationTest;

/**
 * Tests for WindowControl. Based off of TestFX example test documentation.
 *
 * @author Jessica Yang
 */
public class FrontEndTest extends DukeApplicationTest {

  private Button helpButton;
  private Button enterButton;
  private ColorPicker penColor;
  private ColorPicker backgroundColor;
  private ComboBox languageSelector;
  private ImageView turtle1;
  private TextArea commandLine;
  private Rectangle turtleBox;

  @Override
  public void start(Stage stage) {
    WindowControl window = new WindowControl(stage);

    helpButton = lookup("#Help").query();
    enterButton = lookup("#Enter").query();
    penColor = lookup("#pen").query();
    backgroundColor = lookup("#background").query();
    languageSelector = lookup("#language").query();
    turtle1 = lookup("#turtle1").query();
    commandLine = lookup("#commandLine").query();
    turtleBox = lookup("#turtleBox").query();
  }

  @Test
  // scenario: user presses the help button
  void pressHelpButton() {
    clickOn(helpButton);
  }

  @Test
  // scenario: user presses the enter button, with a valid move command in the text field
  void validMoveCommand() {
    double originalY = turtle1.getY();

    commandLine.setText("fd 50");
    clickOn(enterButton);
    assertTrue(turtle1.getY() < originalY);
  }

  @Test
  // scenario: user presses the enter button, with a non-valid command in the text field
  void invalidCommand() {
    commandLine.setText("asdfasdfa");
    clickOn(enterButton);
    assertNotNull(lookup("#errorMessage").query());
  }

  @Test
  // scenario: user changes the color of the pen color
  void changePenColor() {
    String newColor = "#ff6666";
    select(penColor, newColor);
    commandLine.setText("fd 50");
    clickOn(enterButton);
    Line newLine = lookup("#line").query();
    assertTrue(newLine.getFill().toString().equals(Color.valueOf(newColor).toString()));
  }

  @Test
  // scenario: user changes the color of the background
  void changeBackgroundColor() {
    String newColor = "#ff1253";
    select(backgroundColor, newColor);
    assertTrue(turtleBox.getFill().toString().equals(Color.valueOf(newColor).toString()));
  }
}
