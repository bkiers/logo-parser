package lattelogo.node;

import lattelogo.lang.LogoList;
import lattelogo.lang.Value;

import java.util.ArrayList;
import java.util.List;

public class ListNode extends Node {

    private final List<Node> items;

    public ListNode() {
        this.items = new ArrayList<>();
    }

    public void addItem(Node item) {
        this.items.add(item);
    }

    @Override
    public Value eval(Scope scope) {

        LogoList values = new LogoList();

        for (Node item : items) {
            values.add(item.eval(scope));
        }

        return new Value(values);
    }
}
