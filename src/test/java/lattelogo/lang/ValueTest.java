package lattelogo.lang;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ValueTest {

    @Test
    public void isNumber_NormalUse_ShouldPass() {

        assertThat(new Value(1.0).isNumber(), is(true));
        assertThat(new Value(1L).isNumber(), is(true));
        assertThat(new Value(1.000000001).isNumber(), is(true));
        assertThat(new Value("101.00000000").isNumber(), is(true));
        assertThat(new Value("99991.").isNumber(), is(true));
        assertThat(new Value(".10").isNumber(), is(true));
        assertThat(new Value("123").isNumber(), is(true));
    }

    @Test
    public void isInt_NormalUse_ShouldPass() {

        assertThat(new Value(1.0).isInt(), is(true));
        assertThat(new Value(1L).isInt(), is(true));
        assertThat(new Value(1.000000001).isInt(), is(false));
        assertThat(new Value("101.00000000").isInt(), is(true));
        assertThat(new Value("99991.").isInt(), is(true));
        assertThat(new Value(".10").isInt(), is(false));
        assertThat(new Value("123").isInt(), is(true));
    }

    @Test
    public void isFloat_NormalUse_ShouldPass() {

        assertThat(new Value(1.0).isFloat(), is(false));
        assertThat(new Value(1L).isFloat(), is(false));
        assertThat(new Value(1.000000001).isFloat(), is(true));
        assertThat(new Value("101.00000000").isFloat(), is(false));
        assertThat(new Value("99991.").isFloat(), is(false));
        assertThat(new Value(".10").isFloat(), is(true));
        assertThat(new Value("123").isFloat(), is(false));
    }
}
