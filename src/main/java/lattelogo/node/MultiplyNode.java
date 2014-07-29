package lattelogo.node;

public class MultiplyNode extends BinaryExpressionNode {

    public MultiplyNode(Node lhs, Node rhs) {
        super(lhs, rhs, Operator.MULTIPLY);
    }
}
