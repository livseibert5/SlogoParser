package slogo.frontend;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/**
 * Tests for WindowControl. Based off of TestFX example test documentation.
 *
 * @author Jessica Yang
 */
@ExtendWith(ApplicationExtension.class)
public class FrontEndTest {

  @Start
  void onStart(Stage stage) {
    WindowControl window = new WindowControl(stage);
  }
}
