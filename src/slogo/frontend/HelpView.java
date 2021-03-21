package slogo.frontend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class for help window.
 *
 * @author Montana Lee, Jessica Yang
 */
public class HelpView extends ViewMaker {

  private final int HELP_WIDTH;
  private final int HELP_HEIGHT;
  /**
   * Constructor for ErrorView class.
   *
   * @param sizeX x dimension of window
   * @param sizeY y dimension of window
   */
  public HelpView(double sizeX, double sizeY) {
    super(sizeX, sizeY, "Help");
    setUpRoot(createReferenceList());
    HELP_HEIGHT = (int) sizeY;
    HELP_WIDTH = (int) sizeX;
  }

  /**
   * Adds text to the root.
   *
   * @param list of reference methods
   */
  private void setUpRoot(List<String> list) {
    GridPane grid = new GridPane();
    int columns = 6;
    for (int i = 0; i < list.size(); i++) {
      Button link = makeButton(0, 0, list.get(i));
      final int fin = i;
      link.setOnAction(event -> makeHelpPage(list.get(fin)));
      grid.add(link, i % columns, i / columns);
    }

    getRoot().setCenter(grid);
  }

  private void makeHelpPage(String title) {
    Group PopUpRoot = new Group();
    Stage stage = new Stage();
    setText(PopUpRoot, title);
    SceneMaker helpScene = new SceneMaker(PopUpRoot, stage, HELP_WIDTH - 100, HELP_HEIGHT - 100);
    stage.setTitle(title);
  }

  private void setText(Group root, String file) {
    TextArea text = new TextArea();
    text.setPrefSize(HELP_WIDTH - 100, HELP_HEIGHT - 100);
    text.setEditable(false);
    text.setWrapText(true);
    System.out.println(file);
    List<String> lines = new ArrayList<>();
    String read;
    try {
      FileReader fr = new FileReader("src/slogo/frontend/resources/reference/" + file);//read 'file'
      BufferedReader br = new BufferedReader(fr);//read 'file'
      while ((read = br.readLine()) != null) {
        lines.add(read);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (String line : lines) {
      text.appendText(line + "\n");
    }
    root.getChildren().add(text);
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
