package slogo.frontend;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Class for help window.
 *
 * @author Montana Lee, Jessica Yang
 */
public class HelpView extends ViewMaker {

  /**
   * Constructor for ErrorView class.
   *
   * @param sizeX x dimension of window
   * @param sizeY y dimension of window
   */
  public HelpView(double sizeX, double sizeY) {
    super(sizeX, sizeY, "Help");
    setUpRoot(createReferenceList());
  }

  /**
   * Adds text to the root.
   *
   * @param list of reference methods
   */
  private void setUpRoot(List<String> list) {
    VBox formatter = new VBox();
    for (int i = 0; i < list.size(); i++) {
      Button link = makeButton(300, i * 10, list.get(i));
      link.setOnAction(event -> { });
      formatter.getChildren().add(link);
    }
    getRoot().setCenter(formatter);
  }

  private List<String> createReferenceList() {
    List<String> results = new ArrayList<>();
    File[] files = new File("src/slogo/frontend/resources/reference").listFiles();
    assert files != null;
    for (File file : files) {
      if (file.isFile()) {
        results.add(file.getName());
      }
    }
    Collections.sort(results);
    return results;
  }
}
