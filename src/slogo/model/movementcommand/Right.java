package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.movementcommand.MovementCommand;

public class Right extends MovementCommand implements Command {

    private int pixels;

    public Right(Command argument) {
        this.pixels = argument.execute();
    }

    public int getNumberParameters() {
        return 1;
    }

    public int execute() {
        turtle.setOrientation(turtle.getOrientation() - 90);
        int[] newLocation = determineLocation(turtle.getLocation(), turtle.getOrientation(), pixels);
        return pixels;
    }
}
