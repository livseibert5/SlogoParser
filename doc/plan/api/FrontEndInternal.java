/**
 * This interface handles display functionality that does not have to do with the turtle.
 * This includes the IDE, the display of command history, and the layout of the UI as a whole.
 */
interface FrontEndInternal {
    /**
     * This method creates a terminal and sets its position on the screen
     */

    private void createTerminal() {

    }

    /**
     * This method creates the scene of the User Interface, where we will add the terminal and other buttons, assign title, etc.
     *
     * @param stage: needs the stage as an input so it knows where to display the scene
     */

    private void createScene(Stage stage) {

    }

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