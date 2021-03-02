/**
 *  Use case: Use a turtle to draw a square.
 */
// Controller will have event handler to register when the user uses the enter key.
BackEndExternal.parse(command);
// Parser then parses the command.
// Back end executes the command by calling front end external commands.

//Forward command class:
    Command.execute(orientation, amount)
    BackEndInternal.determineLocation(forward, 50);
    // Turtle object will move to the location determined as the result of the command call.
    Turtle.move(BackEndInternal.determineLocation(forward, 50));
    BackEndInternal.determineLocation(right, 50);
    BackEndInternal.determineLocation(right, 50);
    BackEndInternal.determineLocation(right, 50);