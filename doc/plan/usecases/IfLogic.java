/**
 * Use case: User wants to create a custom command with if else logic in it.
 */

IF greater? 3 5 [ commands ]
BackEndExternal.parse(IF expression [ commands ])
BackEndInternal.lessThan(3, 5)
// Once back end internal has parsed the outcome of the if statement, it can then determine whether or not to run the comman.
BackEndExternal.execute()
BackEndExternal.getParserResult()
FrontEndInternal.displayResult()