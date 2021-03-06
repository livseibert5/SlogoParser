package slogo.model.movementcommand;

import slogo.model.Command;

public class SetHeading implements Command {
    double orientation;

    public SetHeading(Command argument){
        this.orientation = argument.execute();
    }

    @Override
    public int getNumberParameters() {
        return 1;
    }

    @Override
    public int execute() {
        turtle.setOrientation(orientation);
        return (int) orientation;
    }
}
