package lattelogo.node;

public class GreaterThanNode extends BinaryExpressionNode {

    public GreaterThanNode(Node lhs, Node rhs) {
        super(lhs, rhs, Operator.GREATER_THAN);
    }
}
