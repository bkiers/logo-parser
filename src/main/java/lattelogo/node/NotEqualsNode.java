package lattelogo.node;

public class NotEqualsNode extends BinaryExpressionNode {

    public NotEqualsNode(Node lhs, Node rhs) {
        super(lhs, rhs, Operator.NOT_EQUALS);
    }
}
