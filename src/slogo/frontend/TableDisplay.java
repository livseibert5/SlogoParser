package slogo.frontend;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

  private TableView<Variable> variableView;
  private TableView<List<String>> commandView;

  private final ObservableList<Variable> allVariables = FXCollections.observableArrayList();

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
    updateVariableView();
    variableView.setItems(allVariables);

    TableColumn<Variable, String> nameColumn = new TableColumn<>("Name" );
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    TableColumn<Variable, Double> valueColumn = new TableColumn<>("Value" );
    valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

    variableView.getColumns().addAll(nameColumn, valueColumn);
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
    extractVariableRows();
    variableView.setItems(allVariables);
  }

  private void updateCommandView() {
    commandView.setItems((ObservableList<List<String>>) extractUserCommandRows());
  }

  private void extractVariableRows() {
    allVariables.clear();

    for (Variable v : myVariableHandler.getAllVariables()) {
      /*
      row.add(v.getName());
      row.add(Double.toString(v.getValue()));

       */
      allVariables.add(v);
    }
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
