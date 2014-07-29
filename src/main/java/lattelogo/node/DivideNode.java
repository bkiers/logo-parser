package lattelogo.node;

public class DivideNode extends BinaryExpressionNode {

    public DivideNode(Node lhs, Node rhs) {
        super(lhs, rhs, Operator.DIVIDE);
    }
}
