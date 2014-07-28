package lattelogo;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.junit.*;

import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.fail;

/**
 * A test class that parses all files containing Logo sources in
 * the folder `src/test/resources`.
 */
public class RegressionTest {

    /**
     * Parses some UCB Logo source which is possibly invalid.
     *
     * @param source
     *         the Logo source to parse.
     * @param error
     *         if {@code error} is null, the parse is expected
     *         to pass, if not, the parse is expected to fail.
     */
    private void parse(String source, String error) {

        try {
            DescriptiveBailErrorListener errorListener = new DescriptiveBailErrorListener();

            UCBLogoLexer lexer = new UCBLogoLexer(new ANTLRInputStream(source));
            lexer.removeErrorListeners();
            lexer.addErrorListener(errorListener);

            UCBLogoParser parser = new UCBLogoParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(errorListener);

            parser.parse();

            if (error != null) {
                fail("expected an error for:\n" + source + "\nexpected: " + error);
            }
        }
        catch (Exception e) {
            if (error == null) {
                fail("could not parse:\n" + source + "\n" + e);
            }
        }
    }

    @Test
    public void parseFiles() throws Exception {

        // Find all files in the folder `src/test/resources` ending with ".test".
        File[] tests = new File("src/test/resources").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".test");
            }
        });

        // A pattern defining source between `<<<` and `>>>`, optionally capturing
        // an error message defined after the closing tag `>>>`.
        Pattern pattern = Pattern.compile("(?s)<<<(?<source>((?!>>>).)*)>>>[ \\t]*(?<error>\\w[^\\r\\n]*)?");

        for (File test : tests) {

            // Read the entire contents of the file.
            String contents = new Scanner(test).useDelimiter("\\Z").next();

            Matcher matcher = pattern.matcher(contents);

            while (matcher.find()) {

                String source = matcher.group("source");
                String error = matcher.group("error");

                parse(source, error);
            }
        }
    }
}
