package lattelogo.parser;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class ParserUtilsTest {

    @Test
    public void parse_InvalidLogoSource_ShouldReturnNull() {
        assertThat(ParserUtils.parse("mu", "script", false), is(nullValue()));
    }

    @Test
    public void parse_ValidLogoSource_ShouldReturnParseTree() {
        assertTrue(ParserUtils.parse("print \"test", "script", false) != null);
        assertTrue(ParserUtils.parse("\"test", "expression", false) != null);
    }

    @Test(expected = RuntimeException.class)
    public void parse_InvalidParserRule_ShouldThrowException() {
        ParserUtils.parse("print \"test", "scriptX", false);
    }
}
