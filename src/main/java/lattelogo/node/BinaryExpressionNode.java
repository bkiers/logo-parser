package lattelogo.node;

public abstract class BinaryExpressionNode extends Node {

    protected final Node lhs;
    protected final Node rhs;
    protected final String operator;

    public BinaryExpressionNode(Node lhs, Node rhs, String operator) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.operator = operator;
    }
}
