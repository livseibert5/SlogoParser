/**
 * Use case: User enters command to find the orientation and the backend returns the correct orientation
 */

BackEndExternal.parse(command);
// Back end internal then executes the command and determines the new location for the turtle.
BackEndInternal.determineLocation();
// Front end can then get the location and the orientation from the back end.
BackEndExternal.getLocation();
BackEndExternal.getOrientation();