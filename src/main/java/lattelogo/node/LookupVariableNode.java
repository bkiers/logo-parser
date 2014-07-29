package lattelogo.node;

import lattelogo.lang.Value;

public class LookupVariableNode extends Node {

    private final String name;

    public LookupVariableNode(String name) {
        this.name = name;
    }

    @Override
    public Value eval(Scope scope) {

        Value value = scope.resolve(this.name);

        if (value == Value.NOTHING) {
            System.err.println(this.name + " has no value");
            return Value.NOTHING;
        }

        return value;
    }
}
