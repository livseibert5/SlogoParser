package slogo.frontend;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import slogo.model.UserDefinedCommand;
import slogo.model.Variable;
import slogo.model.handlers.UserDefinedCommandHandler;
import slogo.model.handlers.VariableHandler;

/**
 * Creates tables for front-end.
 * TODO assumptions, dependencies, example of use
 *
 * @author Jessica Yang
 */
public class TableDisplay {

  private static final int WINDOW_SIZE = 600;
  private static final int DEFAULT_BORDER = 50;

  private VariableHandler myVariableHandler;
  private UserDefinedCommandHandler myCommandHandler;
  private Group myRoot;

  private TableView<List<String>> variableView;
  private TableView<List<String>> commandView;

  /**
   * Constructor for TableDisplay.
   *
   * @param variableHandler retrieves variable information
   * @param commandHandler retrieves custom command information
   * @param root root of display
   */
  public TableDisplay(VariableHandler variableHandler, UserDefinedCommandHandler commandHandler, Group root) {
    myVariableHandler = variableHandler;
    myCommandHandler = commandHandler;
    myRoot = root;
    makeVariableView();
    makeUserCommandView();
  }

  private void makeVariableView() {
    variableView = new TableView<>();
    TableColumn name = new TableColumn("Name" );
    //TableColumn type = new TableColumn("Type" );
    TableColumn value = new TableColumn("Value" );
    variableView.getColumns().addAll(name, value);
    variableView.setPrefSize(WINDOW_SIZE/2 - DEFAULT_BORDER/4, (WINDOW_SIZE - DEFAULT_BORDER) / 2);
    variableView.relocate(DEFAULT_BORDER, DEFAULT_BORDER);

    variableView.setId("variableview");
    myRoot.getChildren().add(variableView);
  }

  private void makeUserCommandView() {
    commandView = new TableView();
    TableColumn command = new TableColumn("Command" );
    TableColumn definition = new TableColumn("Definition" );
    commandView.getColumns().addAll(command, definition);
    commandView.setPrefSize(WINDOW_SIZE/2 - DEFAULT_BORDER/4, (WINDOW_SIZE - DEFAULT_BORDER) / 2);
    commandView.relocate(DEFAULT_BORDER*1.5 + commandView.getPrefWidth(), DEFAULT_BORDER);

    commandView.setId("commandview");
    myRoot.getChildren().add(commandView);
  }

  /**
   * Updates front-end tables for variables and commands.
   */
  public void updateTables() {
    updateVariableView();
    updateCommandView();
  }

  private void updateVariableView() {
    variableView.setItems((ObservableList<List<String>>) extractVariableRows());
  }

  private void updateCommandView() {
    commandView.setItems((ObservableList<List<String>>) extractUserCommandRows());
  }

  private List<List<String>> extractVariableRows() {
    List<List<String>> allVariableRows = FXCollections.observableArrayList();

    for (Variable v : myVariableHandler.getAllVariables()) {
      List<String> row = FXCollections.observableArrayList();
      row.clear();
      row.add(v.getName());
      row.add(Double.toString(v.getValue()));
      allVariableRows.add(row);
    }

    return allVariableRows;
  }


  private List<List<String>> extractUserCommandRows() {
    List<List<String>> allCommandRows = FXCollections.observableArrayList();

    for (UserDefinedCommand c : myCommandHandler.getAllCommands()) {
      List<String> row = FXCollections.observableArrayList();
      row.clear();
      row.add(c.getCommandName());
      row.add(c.getBody().toString());
      allCommandRows.add(row);
    }

    return allCommandRows;
  }
}
