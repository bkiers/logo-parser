package lattelogo.procedure;

import lattelogo.error.DescriptiveBailErrorListener;
import lattelogo.lang.LogoList;
import lattelogo.lang.Value;
import lattelogo.node.Node;
import lattelogo.node.Scope;
import lattelogo.parser.NodeVisitor;
import lattelogo.parser.ParserUtils;
import lattelogo.parser.UCBLogoLexer;
import lattelogo.parser.UCBLogoParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Arrays;

/**
 * <pre>
 * <code>
 * IF tf instructionlist
 * (IF tf instructionlist1 instructionlist2)
 *
 *     command.  If the first input has the value TRUE, then IF runs
 *     the second input.  If the first input has the value FALSE, then
 *     IF does nothing.  (If given a third input, IF acts like IFELSE,
 *     as described below.)  It is an error if the first input is not
 *     either TRUE or FALSE.
 *
 *     For compatibility with earlier versions of Logo, if an IF
 *     instruction is not enclosed in parentheses, but the first thing
 *     on the instruction line after the second input expression is a
 *     literal list (i.e., a list in square brackets), the IF is
 *     treated as if it were IFELSE, but a warning message is given.
 *     If this aberrant IF appears in a procedure body, the warning is
 *     given only the first time the procedure is invoked in each Logo
 *     session.
 * </code>
 * </pre>
 */
public class If extends Procedure {

    protected If() {
        super("if", 2, true);
    }

    @Override
    public Value invoke(Scope scope, Value... params) {

        checkParams(params);

        Value condition = params[0];

        if (!condition.isBoolean()) {
            throw new RuntimeException("if doesn't like " + condition + " as input");
        }

        Value possibleList = null;

        if (condition.asBoolean()) {
            possibleList = params[1];
        }
        else if (params.length >= 3) {
            possibleList = params[2];
        }

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
