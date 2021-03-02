/**
 * The user sets a variable's value and sees it updated in the UI's Variable view.
 */

 BackEndExternal.parse(command);
 // Parser then parses the command.
  BackEndExternal.getParserResult();
  //For this case, the parser result is used to update the variable view in addition to returning the value in the terminal
FrontEndInternal.updateVariableView(int value);