/**
 * User calls command that is not within the scope of defined slogo commands
 */
// Controller will have event handler to register when the user uses the enter key.
BackEndExternal.parse(command);
// Parser then parses the command.
// Parser doesn't recognize the input, throws Exception from custom exceptions class.
// Exception displays as a pop up box on the front end.
FrontEndInternal.displayErrorBox(String errorMessage);