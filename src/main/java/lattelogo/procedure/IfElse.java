package lattelogo.procedure;

import lattelogo.error.DescriptiveBailErrorListener;
import lattelogo.lang.LogoList;
import lattelogo.lang.Value;
import lattelogo.node.Node;
import lattelogo.node.Scope;
import lattelogo.parser.NodeVisitor;
import lattelogo.parser.UCBLogoParser;
import org.antlr.v4.runtime.ANTLRInputStream;

/**
 * <pre>
 * <code>
 * IFELSE tf instructionlist1 instructionlist2
 *
 *     command or operation.  If the first input has the value TRUE, then
 *     IFELSE runs the second input.  If the first input has the value FALSE,
 *     then IFELSE runs the third input.  IFELSE outputs a value if the
 *     instructionlist contains an expression that outputs a value.
 * </code>
 * </pre>
 */
public class IfElse extends Procedure {

    protected IfElse() {
        super("ifelse", 3, false);
    }

    @Override
    public Value invoke(Scope scope, Value... params) {

        checkParams(params);

        Value condition = params[0];

        if (!condition.isBoolean()) {
            throw new RuntimeException("ifelse doesn't like " + condition + " as input");
        }

        Value possibleList = condition.asBoolean() ? params[1] : params[2];

        if (possibleList.isList()) {

            // TODO refactor the parser instantiation

            Value value = Value.NOTHING;
            LogoList list = possibleList.asList();
            UCBLogoParser parser = new UCBLogoParser(new ANTLRInputStream(list.asSource()), false);
            parser.removeErrorListeners();
            parser.addErrorListener(DescriptiveBailErrorListener.INSTANCE);

            NodeVisitor visitor = new NodeVisitor();

            try {
                Node root = visitor.visit(parser.script());
                value = root.eval(scope);
            }
            catch (Exception e) {
                try {
                    parser = new UCBLogoParser(new ANTLRInputStream(list.asSource()), false);
                    parser.removeErrorListeners();
                    parser.addErrorListener(DescriptiveBailErrorListener.INSTANCE);
                    Node root = visitor.visit(parser.expression());
                    value = root.eval(scope);
                }
                catch (Exception ex) {
                    // Ignore
                }
            }

            if (value != Value.NOTHING) {
                return value;
            }
        }
        else {
            return scope.resolve(possibleList.asString());
        }

        return Value.NOTHING;
    }
}
