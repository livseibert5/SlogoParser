/**
 * The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.
 */
BackEndExternal.parse("fd 50");
// From back end internal movement command class
BackEndInternal.determineLocation(forward, 50);
// This internal move command will call the turtle move command from the external api
FrontEndExternal.move(endLocation);
BackEndExternal.getParserResult();
FrontEndInternal.display(result);
FrontEndInternal.addToLog("fd 50");