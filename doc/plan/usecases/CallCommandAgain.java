/**
 * User defines a new command to make a square and wants to call it again.
 */

// to square [ :side ]
// [
//   poly 4 :side
// ]

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
// Back end associates the String for the command with the command's name in a map.
// Controller calls getUserCommands() to access this map.
BackEndExternal.getUserCommands();
// Front end displays the name of this command in the UI
FrontEndInternal.addToFunctionBox();