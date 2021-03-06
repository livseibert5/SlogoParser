package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.movementcommand.MovementCommand;

/**
 * Right class is the Command subclass that determines the end location of
 * the turtle when moved right by a certain number of pixels.
 *
 * @author Rachel Luria and Livia Seibert
 */
public class Right extends MovementCommand implements Command {

    private int pixels;

    /**
     * Constructor for the Right command, takes a constant as an argument
     * and gets its value by executing the Constant command.
     *
     * @param argument Constant command type containing the number of pixels to move the turtle
     */
    public Right(Command argument) {
        this.pixels = argument.execute();
    }

    /**
     * Allows access to number of parameters Right requires so Parser can determine when
     * the proper argument is available to execute the right command.
     *
     * @return 1, as Right's only parameter is pixels to be moved
     */
    public int getNumberParameters() {
        return 1;
    }

    /**
     * Turns turtle to the right, then calculates the end location of the turtle based on the
     * number of pixels passed as an argument.
     *
     * @return number of pixels moved
     */
    public int execute() {
        turtle.setOrientation(turtle.getOrientation() - 90);
        int[] newLocation = determineLocation(turtle.getLocation(), turtle.getOrientation(), pixels);
        return pixels;
    }
}
