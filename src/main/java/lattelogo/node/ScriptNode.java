package lattelogo.node;

import lattelogo.lang.Value;

import java.util.ArrayList;
import java.util.List;

public class ScriptNode extends Node {

    private final Scope scriptScope;
    private final List<Node> instructions;

    public ScriptNode() {
        this.scriptScope = new Scope();
        this.instructions = new ArrayList<>();
    }

    public void addInstruction(Node instruction) {
        this.instructions.add(instruction);
    }

    @Override
    public Value eval(Scope scope) {

        if (scope != null) {
            // TODO set parent of this scriptScope?
        }

        for (Node node : instructions) {

            Value value = node.eval(scriptScope);

            if (value != Value.NOTHING) {
                return value;
            }
        }

        return Value.NOTHING;
    }
}
