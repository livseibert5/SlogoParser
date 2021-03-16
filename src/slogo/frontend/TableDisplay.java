package slogo.frontend;

import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import slogo.model.UserDefinedCommand;
import slogo.model.Variable;
import slogo.model.handlers.UserDefinedCommandHandler;
import slogo.model.handlers.VariableHandler;

public class TableDisplay {

  private static final int WINDOW_SIZE = 600;
  private static final int DEFAULT_BORDER = 50;

  private VariableHandler myVariableHandler;
  private UserDefinedCommandHandler myCommandHandler;
  private Group myRoot;

  private TableView<Variable> variableView;
  private TableView<UserDefinedCommand> commandView;

  public TableDisplay(VariableHandler variableHandler, UserDefinedCommandHandler commandHandler, Group root) {
    myVariableHandler = variableHandler;
    myCommandHandler = commandHandler;
    myRoot = root;
    makeVariableView();
    makeUserCommandView();
  }

  /**
   * Updates front-end tables for variables and commands.
   */
  public void updateTables() {
    updateVariableView();
    updateCommandView();
  }

  private void updateVariableView() {
    variableView.getItems().setAll(myVariableHandler.getVariables());
  }

  private void updateCommandView() {
    commandView.getItems().setAll(myCommandHandler.getCommands());
    commandView.getItems().setAll(myCommandHandler.getCommands());
  }

  private void makeVariableView() {
    variableView = new TableView();
    TableColumn name = new TableColumn("Name" );
    TableColumn type = new TableColumn("Type" );
    TableColumn value = new TableColumn("Value" );
    variableView.getColumns().addAll(name, type, value);
    variableView.setPrefSize(WINDOW_SIZE/2 - DEFAULT_BORDER/4, (WINDOW_SIZE - DEFAULT_BORDER) / 2);
    variableView.relocate(DEFAULT_BORDER, DEFAULT_BORDER);
    myRoot.getChildren().add(variableView);
  }

  private void makeUserCommandView() {
    commandView = new TableView();
    TableColumn command = new TableColumn("Command" );
    TableColumn definition = new TableColumn("Definition" );
    commandView.getColumns().addAll(command, definition);
    commandView.setPrefSize(WINDOW_SIZE/2 - DEFAULT_BORDER/4, (WINDOW_SIZE - DEFAULT_BORDER) / 2);
    commandView.relocate(DEFAULT_BORDER*1.5 + commandView.getPrefWidth(), DEFAULT_BORDER);
    myRoot.getChildren().add(commandView);
  }
}
