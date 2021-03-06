package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * Left class is the Command subclass that determines the end location of
 * the turtle when moved left by a certain number of pixels.
 *
 * @author Rachel Luria and Livia Seibert
 */
public class Left extends MovementCommand implements Command {

    private int pixels;
    private Command argument;

    /**
     * Constructor for the Left command, takes a constant as an argument
     * and gets its value by executing the Constant command.
     *
     * @param argument Constant command type containing the number of pixels to move the turtle
     */
    public Left(Command argument) {
        this.argument = argument;
    }

    /**
     * Allows access to number of parameters Left requires so Parser can determine when
     * the proper argument is available to execute the left command.
     *
     * @return 1, as Left's only parameter is pixels to be moved
     */
    @Override
    public int getNumberParameters() {
        return 1;
    }

    /**
     * Turns turtle to the left, then calculates the end location of the turtle based on the
     * number of pixels passed as an argument.
     *
     * @param turtle turtle object to execute command on
     * @return number of pixels moved
     */
    @Override
    public int execute(Turtle turtle) {
        this.pixels = argument.execute(turtle);
        turtle.setOrientation(turtle.getOrientation() + 90);
        int[] newLocation = determineLocation(turtle.getLocation(), turtle.getOrientation(), pixels);
        return pixels;
    }
}