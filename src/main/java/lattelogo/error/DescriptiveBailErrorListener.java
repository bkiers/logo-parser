package lattelogo.error;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * An error listener that immediately bails out of the parse (does not recover)
 * and throws a runtime exception with a descriptive error message.
 */
public class DescriptiveBailErrorListener extends BaseErrorListener {

    public static final DescriptiveBailErrorListener INSTANCE = new DescriptiveBailErrorListener();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e) {

        String entireMessage = String.format("source: %s, line: %s, index: %s, error message: %s %s",
                recognizer.getInputStream().getSourceName(), line, charPositionInLine, msg,
                e == null ? "<null>" : e.getMessage());

        throw new RuntimeException(entireMessage);
    }
}