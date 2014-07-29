package lattelogo.node;

import lattelogo.lang.Value;
import lattelogo.procedure.Procedure;

import java.util.ArrayList;
import java.util.List;

public class ProcedureCallNode extends Node {

    private final String procedureName;
    private final List<Node> expressions;

    public ProcedureCallNode(String procedureName, List<Node> expressions) {
        this.procedureName = procedureName;
        this.expressions = expressions;
    }

    @Override
    public Value eval(Scope scope) {

        Procedure procedure = Procedure.get(this.procedureName);

        List<Value> values = new ArrayList<>();

        for (Node param : expressions) {
            values.add(param.eval(scope));
        }

        return procedure.invoke(scope, values);
    }
}
