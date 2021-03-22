package slogo.frontend;

import java.awt.event.KeyEvent;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
  private Button turtleDetailButton;
  private ComboBox languageSelector;
  private ImageView turtle1;
  private TextArea commandLine;
  private Rectangle turtleBox;
  private TableView commandView;
  private TableView variableView;

  @Override
  public void start(Stage stage) {
    WindowControl window = new WindowControl(stage);

    helpButton = lookup("#Help").query();
    enterButton = lookup("#Enter").query();
    turtleDetailButton = lookup("#TurtleDetails").query();
    languageSelector = lookup("#language").query();
    turtle1 = lookup("#turtle1").query();
    commandLine = lookup("#commandLine").query();
    turtleBox = lookup("#turtleBox").query();
    commandView = lookup("#commandview").query();
    variableView = lookup("#variableview").query();
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
  // scenario: user moves the turtle back
  void backNoLine() {
    commandLine.setText("bk 50");
    clickOn(enterButton);
  }

  @Test
  // scenario: user draws a square
  void drawSquare() {
    commandLine.setText("pd");
    clickOn(enterButton);
    assertTrue(commandLine.getText().equals(Integer.toString(1)));
    for (int i = 0; i < 4; i++) {
      commandLine.setText("fd 50");
      clickOn(enterButton);
      assertTrue(commandLine.getText().equals(Integer.toString(50)));
      commandLine.setText("right 90");
      clickOn(enterButton);
      assertTrue(commandLine.getText().equals(Integer.toString(90)));
    }
  }

  @Test
  // scenario: user removes turtle from view, moves, then adds it back to view
  void hideTurtle() {
    commandLine.setText("pd");
    clickOn(enterButton);
    assertTrue(commandLine.getText().equals(Integer.toString(1)));
    commandLine.setText("hideturtle");
    clickOn(enterButton);
    assertTrue(commandLine.getText().equals(Integer.toString(0)));
    commandLine.setText("fd 50");
    clickOn(enterButton);
    assertTrue(commandLine.getText().equals(Integer.toString(50)));
    commandLine.setText("st");
    clickOn(enterButton);
    assertTrue(commandLine.getText().equals(Integer.toString(1)));
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
  }

  @Test
  // scenario: user changes the color of the background and draws a line
  void changeBackgroundColor() {

  }

  @Test
  // scenario: user adds a variable
  void addVariable() {
    String varName = "make :newvar 10";
    commandLine.setText(varName);
    clickOn(enterButton);
    assertTrue(1 == variableView.getItems().size());
  }

  @Test
  // scenario: user adds a user defined command
  void addCommand() {
    String command = "to makethree [ :sum ] [ sum 1 2 ]";
    commandLine.setText(command);
    clickOn(enterButton);
    assertTrue(1 == (commandView.getItems().size()));
  }

  @Test
  // scenario: user adds an additional turtle
  void addTurtle() {
    assertNotNull(lookup("#turtle1").query());
    String command = "tell [ 2 ]";
    commandLine.setText(command);
    clickOn(enterButton);
    assertNotNull(lookup("#turtle2").query());
  }

  @Test
  // scenario: user moves two turtles independently
  void multiTurtleMove() {
    String addTurtles = "tell [ 1 2 ]";
    commandLine.setText(addTurtles);
    clickOn(enterButton);

    commandLine.setText("pd");
    clickOn(enterButton);

    commandLine.setText("tell [ 1 ]");
    clickOn(enterButton);
    commandLine.setText("fd 200");
    clickOn(enterButton);

    commandLine.setText("tell [ 2 ]");
    clickOn(enterButton);
    commandLine.setText("fd 50");
    clickOn(enterButton);

    ImageView turtle1 = lookup("#turtle1").query();
    ImageView turtle2 = lookup("#turtle2").query();
    assertTrue(turtle1.getY() < turtle2.getY());
  }

  @Test
  // scenario: user opens turtledetailviewer to watch default turtle
  void singleTurtleDetailViewer() {
    clickOn(turtleDetailButton);
    ComboBox<Integer> ids = lookup("#turtleIds").query();
    assertNotNull(ids);

    select(ids, "1");

    Text yLocation = lookup("#yLocation").query();
    assertTrue(yLocation.getText().equals("0.0"));

    commandLine.setText("fd 50");
    clickOn(enterButton);
    assertTrue(yLocation.getText().equals("50.0"));
  }

  @Test
  // scenario: user opens turtledetailview to watch multiple turtles
  void multiTurtleDetailViewer() {
    clickOn(turtleDetailButton);
    ComboBox<Integer> ids = lookup("#turtleIds").query();
    Text yLocation = lookup("#yLocation").query();

    select(ids, "1");
    commandLine.setText("fd 50");
    clickOn(enterButton);
    assertTrue(yLocation.getText().equals("50.0"));

    commandLine.setText("tell [ 2 ]");
    clickOn(enterButton);
    select(ids, "2");
    commandLine.setText("fd 100");
    clickOn(enterButton);
    assertTrue(yLocation.getText().equals("100.0"));
  }
}
