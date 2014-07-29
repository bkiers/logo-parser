package lattelogo.node;

public class AddNode extends BinaryExpressionNode {

    public AddNode(Node lhs, Node rhs) {
        super(lhs, rhs, Operator.ADD);
    }
}
