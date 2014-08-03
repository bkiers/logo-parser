package lattelogo.procedure;

import lattelogo.lang.LogoList;
import lattelogo.lang.Value;
import lattelogo.node.Node;
import lattelogo.node.Scope;
import lattelogo.parser.NodeVisitor;
import lattelogo.parser.ParserUtils;

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

        if (possibleList != null) {

            if (possibleList.isList()) {

                Value value = Value.NOTHING;
                NodeVisitor visitor = new NodeVisitor();
                LogoList list = possibleList.asList();


                try {
                    Node root = visitor.visit(ParserUtils.parse(list.asSource(), "script", false));
                    value = root.eval(scope);
                }
                catch (Exception e) {
                    try {
                        Node root = visitor.visit(ParserUtils.parse(list.asSource(), "expression", false));
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
        }

        return Value.NOTHING;
    }
}
