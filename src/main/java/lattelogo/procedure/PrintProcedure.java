package lattelogo.procedure;

import lattelogo.lang.Value;
import lattelogo.node.Scope;

public class PrintProcedure extends Procedure {

    public PrintProcedure() {
        super(new String[]{"print", "pr"}, 1);
    }

    @Override
    public Value invoke(Scope scope, Value... params) {

        Value value = params[0];

        if (value != Value.NOTHING) {
            System.out.println(value);
        }

        return Value.NOTHING;
    }
}
