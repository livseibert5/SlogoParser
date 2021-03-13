package slogo.frontend;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

/**
 * Tests for WindowControl. Based off of TestFX example test documentation.
 *
 * @author Jessica Yang
 */
@ExtendWith(ApplicationExtension.class)
public class FrontEndTest extends ApplicationTest {

  private Button helpButton;
  private Button enterButton;
  private ColorPicker penColor;
  private ColorPicker backgroundColor;
  private ComboBox languageSelector;

  @Start
  void onStart(Stage stage) {
    WindowControl window = new WindowControl(stage);

    helpButton = lookup("#help").query();
    enterButton = lookup("#enter").query();
    penColor = lookup("#pen").query();
    backgroundColor = lookup("#background").query();
    languageSelector = lookup("language").query();
  }


}
