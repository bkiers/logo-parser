package lattelogo.node;

import lattelogo.lang.Value;

import java.util.ArrayList;
import java.util.List;

public class ValueNode extends Node {

    private final Value value;

    public ValueNode(Value value) {
        this.value = value;
    }

    @Override
    public Value eval(Scope scope) {
        return value;
    }
}
