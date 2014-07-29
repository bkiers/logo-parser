package lattelogo.node;

public class LessThanEqualsNode extends BinaryExpressionNode {

    public LessThanEqualsNode(Node lhs, Node rhs) {
        super(lhs, rhs, Operator.LESS_THAN_EQUALS);
    }
}
