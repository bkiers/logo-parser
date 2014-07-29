package lattelogo.node;

public class LessThanNode extends BinaryExpressionNode {

    public LessThanNode(Node lhs, Node rhs) {
        super(lhs, rhs, Operator.LESS_THAN);
    }
}
