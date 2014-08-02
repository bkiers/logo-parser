package lattelogo.node;

import lattelogo.lang.Value;

import java.util.HashMap;
import java.util.Map;

public class Scope {

    private Scope parent;
    private final Map<String, Value> values;

    public Scope() {
        this.values = new HashMap<>();
    }

    public void assign(String name, Value value) {
        this.values.put(name, value);
    }

    public Value resolve(String name) {

        if (this.values.containsKey(name)) {
            return values.get(name);
        }

        if (this.parent != null) {
            return this.parent.resolve(name);
        }

        throw new RuntimeException(name + " has no value");
    }

    public void setParent(Scope parent) {
        this.parent = parent;
    }
}
