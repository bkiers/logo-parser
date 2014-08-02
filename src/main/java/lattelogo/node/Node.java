package lattelogo.node;

import lattelogo.lang.Value;

public abstract class Node {

    public Value eval() {
        return this.eval(null);
    }

    public abstract Value eval(Scope scope);
}
