package lattelogo.procedure;

import lattelogo.lang.Value;
import lattelogo.node.Scope;

/**
 * <pre>
 * <code>
 * PRINT thing
 * PR thing
 * (PRINT thing1 thing2 ...)
 * (PR thing1 thing2 ...)
 *
 *     command.  Prints the input or inputs to the current write stream
 *     (initially the screen).  All the inputs are printed on a single
 *     line, separated by spaces, ending with a newline.  If an input is a
 *     list, square brackets are not printed around it, but brackets are
 *     printed around sublists.  Braces are always printed around arrays.
 * </code>
 * </pre>
 */
public class Print extends Procedure {

    protected Print() {
        super(new String[]{"print", "pr"}, 1, true);
    }

    @Override
    public Value invoke(Scope scope, Value... params) {

        checkParams(params);

        for (Value value : params) {
            System.out.print(value + " ");
        }

        return Value.NOTHING;
    }
}
