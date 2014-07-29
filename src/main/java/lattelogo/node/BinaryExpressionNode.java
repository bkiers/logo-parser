package lattelogo.node;

import lattelogo.lang.LSystem;
import lattelogo.lang.Value;

public abstract class BinaryExpressionNode extends Node {

    public static enum Operator {

        ADD("+"),
        SUBTRACT("-"),
        MULTIPLY("*"),
        DIVIDE("/");

        private final String label;

        private Operator(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    protected final Node lhs;
    protected final Node rhs;
    protected final Operator operator;

    public BinaryExpressionNode(Node lhs, Node rhs, Operator operator) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.operator = operator;
    }

    @Override
    public Value eval(Scope scope) {

        Value left = this.lhs.eval(scope);
        Value right = this.rhs.eval(scope);

        if (left.isNumber() && right.isNumber()) {
            switch (this.operator) {
                case ADD:
                    return new Value(left.asDouble() + right.asDouble());
                case SUBTRACT:
                    return new Value(left.asDouble() - right.asDouble());
                case MULTIPLY:
                    return new Value(left.asDouble() * right.asDouble());
                case DIVIDE:
                    return new Value(left.asDouble() / right.asDouble());
                default:
                    throw new RuntimeException("unknown operator: " + this.operator);
            }
        }

        // At least one of the values is not a number.
        if (!left.isNumber()) {
            LSystem.err.println(this.operator + " doesn't like " + left + " as input");
        }
        else {
            LSystem.err.println(this.operator + " doesn't like " + right + " as input");
        }

        return Value.NOTHING;
    }
}
