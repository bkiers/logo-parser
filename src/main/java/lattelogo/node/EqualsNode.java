package lattelogo.node;

public class EqualsNode extends BinaryExpressionNode {

    public EqualsNode(Node lhs, Node rhs) {
        super(lhs, rhs, Operator.EQUALS);
    }
}
