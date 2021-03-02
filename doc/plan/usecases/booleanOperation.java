/**
 * Use case: User enters a boolean operation in the terminal and the backend successfully calculates and returns
 * 1 or 0
 */
BackEndExternal.parse(command);
// Parser determines that the command is an and statement and sends it to the Boolean logic command class.
    BackEndInternal.and(a, b);
// Controller gets the result from the parser.
BackEndExternal.getParserResult();
FrontEndInternal.displayResult();