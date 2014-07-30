package lattelogo.procedure;

import lattelogo.lang.Value;
import lattelogo.node.Scope;

/**
 * <pre>
 * <code>
 * MAKE varname value
 *
 *     command.  Assigns the value "value" to the variable named "varname",
 *     which must be a word.  Variable names are case-insensitive.  If a
 *     variable with the same name already exists, the value of that
 *     variable is changed.  If not, a new global variable is created.
 * </code>
 * </pre>
 */
public class Make extends Procedure {

    protected Make() {
        super("make", 1, false);
    }

    @Override
    public Value invoke(Scope scope, Value... params) {

        checkParams(params);

        Value name = params[0];
        Value value = params[1];

        scope.assign(name.asString(), value);

        return Value.NOTHING;
    }
}
