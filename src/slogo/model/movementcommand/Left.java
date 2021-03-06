package slogo.model.movementcommand;

import slogo.model.Command;

public class Left extends MovementCommand implements Command {

    private int pixels;

    public Left(Command argument) {
        this.pixels = argument.execute();
    }

    @Override
    public int getNumberParameters() {
        return 1;
    }

    @Override
    public int execute() {
        int[] newLocation = determineLocation(turtle.getLocation(), turtle.getOrientation(), pixels);
        return pixels;
    }
}
