package lattelogo.parser;

import lattelogo.error.DescriptiveBailErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.lang.reflect.Method;

public class ParserUtils {

    public static ParseTree parse(String source, String ruleName, boolean discoverProcedures) {

        try {
            UCBLogoParser parser = new UCBLogoParser(new ANTLRInputStream(source), discoverProcedures);
            parser.removeErrorListeners();
            parser.addErrorListener(DescriptiveBailErrorListener.INSTANCE);
            Method rule = parser.getClass().getDeclaredMethod(ruleName);
            return (ParseTree) rule.invoke(parser);
        }
        catch (NoSuchMethodException e) {
            throw new RuntimeException("no such rule: " + ruleName);
        }
        catch (Throwable t) {
            return null;
        }
    }
}
