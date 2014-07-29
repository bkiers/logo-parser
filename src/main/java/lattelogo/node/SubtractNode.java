package lattelogo.node;

public class SubtractNode extends BinaryExpressionNode {

    public SubtractNode(Node lhs, Node rhs) {
        super(lhs, rhs, Operator.SUBTRACT);
    }
}
