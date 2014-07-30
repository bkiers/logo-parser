package lattelogo.procedure;

import lattelogo.lang.Value;
import lattelogo.node.Scope;

/**
 * THING varname
 * :quoted.varname
 *
 *     outputs the value of the variable whose name is the input.
 *     If there is more than one such variable, the innermost local
 *     variable of that name is chosen.  The colon notation is an
 *     abbreviation not for THING but for the combination
 *
 *     thing "
 *
 *     so that :FOO means THING "FOO.
 */
public class Thing extends Procedure {

    protected Thing() {
        super("thing", 1, false);
    }

    @Override
    public Value invoke(Scope scope, Value... params) {

        super.checkParams(params);

        String name = String.valueOf(params[0]);

        return scope.resolve(name);
    }
}
