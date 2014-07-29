package lattelogo.node;

import lattelogo.lang.LSystem;
import lattelogo.lang.Value;

public class SubtractNode extends BinaryExpressionNode {

    public SubtractNode(Node lhs, Node rhs) {
        super(lhs, rhs, "-");
    }

    @Override
    public Value eval(Scope scope) {

        Value left = super.lhs.eval(scope);
        Value right = super.rhs.eval(scope);

        if (left.isNumber() && right.isNumber()) {
            if (left.isInt() && right.isInt()) {
                return new Value(left.asLong() - right.asLong());
            }
            else {
                return new Value(left.asDouble() - right.asDouble());
            }
        }

        // At least one of the values is not a number.
        if (!left.isNumber()) {
            LSystem.err.println(super.operator + " doesn't like " + left + " as input");
        }
        else {
            LSystem.err.println(super.operator + " doesn't like " + right + " as input");
        }

        return Value.NOTHING;
    }
}
