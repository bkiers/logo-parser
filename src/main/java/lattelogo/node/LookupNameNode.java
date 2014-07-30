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
        return scope.resolve(this.name);
    }
}
