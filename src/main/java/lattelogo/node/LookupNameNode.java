package lattelogo.node;

import lattelogo.lang.Value;
import lattelogo.procedure.Procedure;

import java.util.ArrayList;
import java.util.List;

public class LookupNameNode extends Node {

    private final String name;

    public LookupNameNode(String name) {
        this.name = name;
    }

    @Override
    public Value eval(Scope scope) {

        Value value = scope.resolve(this.name);

        if (value == Value.NOTHING) {
            System.err.println("I don't know how to " + this.name);
            return Value.NOTHING;
        }

        return value;
    }
}
