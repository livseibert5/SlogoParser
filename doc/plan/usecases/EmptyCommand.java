/**
* User presses the enter button to run a command but they have not written anything in the terminal
*/

BackEndExternal.parse(command);
// Parser then attempts to parse the command.
// Parser recognizes the input as empty, throws Exception from custom exceptions class.
// Exception displays as a pop up box on the front end, telling the user there was no command in the terminal.
FrontEndInternal.displayErrorBox(String errorMessage);