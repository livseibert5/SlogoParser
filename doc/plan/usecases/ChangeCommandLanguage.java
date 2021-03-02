/**
 * Use case: User types a command in Russian.
 */
// Let user determine which resource bundle should be used to parse the input.
BackEndExternal.setLanguage()
// Parser uses the resource bundle determined by the user to determine the functionality of the command.
BackEndExternal.parse(command)