/**
 * Use case: Draw a dotted line using the turtle.
 */
BackEndExternal.parse(command)
// Back end internal calls pen up and pen down at intervals to make the line appear dotted.
BackEndInternal.setPen(true)
BackEndInternal.setPen(false)
BackEndInternal.setPen(true)
// This would make the turtle object on the front end draw the line while the pen was down and stop drawing when the line was up.
