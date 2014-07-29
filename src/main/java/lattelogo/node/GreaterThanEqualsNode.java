package lattelogo.node;

public class GreaterThanEqualsNode extends BinaryExpressionNode {

    public GreaterThanEqualsNode(Node lhs, Node rhs) {
        super(lhs, rhs, Operator.GREATER_THAN_EQUALS);
    }
}
