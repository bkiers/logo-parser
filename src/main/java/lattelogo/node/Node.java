package lattelogo.node;

import lattelogo.lang.Value;

public abstract class Node {

    public abstract Value eval(Scope scope, Node... params);
}
