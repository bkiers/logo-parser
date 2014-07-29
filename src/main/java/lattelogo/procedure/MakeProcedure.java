package lattelogo.procedure;

import lattelogo.lang.Value;
import lattelogo.node.Scope;

public class MakeProcedure extends Procedure {

    public MakeProcedure() {
        super("make", 1);
    }

    @Override
    public Value invoke(Scope scope, Value... params) {

        Value name = params[0];
        Value value = params[1];

        scope.assign(name.asString(), value);

        return Value.NOTHING;
    }
}
