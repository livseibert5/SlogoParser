package slogo.frontend;

import javafx.stage.Stage;

public interface FrontEndInternal {
    abstract void createTerminal();


    /**
     * Takes a function from the terminal and adds it to the command history, which will either be contained in the terminal or held in a separate box
     * Ensures the event handler moves past actions to the log and doesn't call the same command multiple times
     */
    private void addToLog() {

    }
    private void createVariableView() {

    }
    private void createErrorBox() {

    }
    private void updateErrorBox() {

    }
    private void updateVariables() {

    }
}
