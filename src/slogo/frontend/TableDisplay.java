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

  private final ObservableList<Variable> allVariables;
  private final ObservableList<UserDefinedCommand> allCommands;

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
    allVariables = (ObservableList<Variable>) myVariableHandler.getAllVariables();
    allCommands = (ObservableList<UserDefinedCommand>) myCommandHandler.getAllCommands();
    makeVariableView();
    makeUserCommandView();
  }

  private void makeVariableView() {
    TableView<Variable> variableView = new TableView<>();
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
    TableView<UserDefinedCommand> commandView = new TableView<>();
    commandView.setItems(allCommands);

    TableColumn<UserDefinedCommand, String> commandColumn = new TableColumn<>("Command" );
    commandColumn.setCellValueFactory(new PropertyValueFactory<>("commandName"));
    TableColumn<UserDefinedCommand, String> bodyColumn = new TableColumn<>("Body" );
    bodyColumn.setCellValueFactory(new PropertyValueFactory<>("body"));

    commandView.getColumns().addAll(commandColumn, bodyColumn);
    commandView.setPrefSize(WINDOW_SIZE/2 - DEFAULT_BORDER/4, (WINDOW_SIZE - DEFAULT_BORDER) / 2);
    commandView.relocate(DEFAULT_BORDER*1.5 + commandView.getPrefWidth(), DEFAULT_BORDER);

    commandView.setId("commandview");
    myRoot.getChildren().add(commandView);
  }
}
