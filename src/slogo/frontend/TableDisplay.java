package slogo.frontend;

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

  private static final double WINDOW_SIZE = 600;
  private static final double DEFAULT_BORDER = 50;

  private final Group myRoot;

  private final ObservableList<Variable> allVariables;
  private final ObservableList<UserDefinedCommand> allCommands;

  /**
   * Constructor for TableDisplay.
   *
   * @param variableHandler retrieves variable information
   * @param commandHandler retrieves custom command information
   * @param root root of display
   */
  public TableDisplay(VariableHandler variableHandler, UserDefinedCommandHandler commandHandler,
      Group root) {
    myRoot = root;
    allVariables = (ObservableList<Variable>) variableHandler.getAllVariables();
    allCommands = (ObservableList<UserDefinedCommand>) commandHandler.getAllCommands();
    makeVariableView();
    makeUserCommandView();
  }

  private void makeVariableView() {
    TableView<Variable> variableView = new TableView<>();
    variableView.setPrefSize(WINDOW_SIZE / 2 - DEFAULT_BORDER / 4,
        (WINDOW_SIZE - DEFAULT_BORDER) / 2);
    variableView.relocate(DEFAULT_BORDER, DEFAULT_BORDER);
    variableView.setItems(allVariables);

    TableColumn<Variable, String> nameColumn = new TableColumn<>("Name");
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    nameColumn.setPrefWidth(variableView.getPrefWidth() / 2);
    variableView.getColumns().add(nameColumn);

    TableColumn<Variable, Double> valueColumn = new TableColumn<>("Value");
    valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
    valueColumn.setPrefWidth(variableView.getPrefWidth() / 2);
    variableView.getColumns().add(valueColumn);

    variableView.setId("variableview");
    myRoot.getChildren().add(variableView);
  }

  private void makeUserCommandView() {
    TableView<UserDefinedCommand> commandView = new TableView<>();
    commandView.setPrefSize(WINDOW_SIZE / 2 - DEFAULT_BORDER / 4,
        (WINDOW_SIZE - DEFAULT_BORDER) / 2);
    commandView.relocate(DEFAULT_BORDER * 1.5 + commandView.getPrefWidth(), DEFAULT_BORDER);
    commandView.setItems(allCommands);

    TableColumn<UserDefinedCommand, String> commandColumn = new TableColumn<>("Command");
    commandColumn.setCellValueFactory(new PropertyValueFactory<>("commandName"));
    commandColumn.setPrefWidth(commandView.getPrefWidth() / 3);
    commandView.getColumns().add(commandColumn);

    TableColumn<UserDefinedCommand, String> bodyColumn = new TableColumn<>("Body");
    bodyColumn.setCellValueFactory(new PropertyValueFactory<>("body"));
    bodyColumn.setPrefWidth(commandView.getPrefWidth() * (2.0 / 3));
    commandView.getColumns().add(bodyColumn);

    commandView.setId("commandview");
    myRoot.getChildren().add(commandView);
  }
}
