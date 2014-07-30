package lattelogo.node;

import lattelogo.lang.LogoArray;
import lattelogo.lang.Value;

import java.util.ArrayList;
import java.util.List;

public class ArrayNode extends Node {

    private final List<Node> items;

    public ArrayNode() {
        this.items = new ArrayList<>();
    }

    public void addItem(Node item) {
        this.items.add(item);
    }

    @Override
    public Value eval(Scope scope) {

        LogoArray values = new LogoArray();

        for (Node item : items) {
            values.add(item.eval(scope));
        }

        return new Value(values);
    }
}
