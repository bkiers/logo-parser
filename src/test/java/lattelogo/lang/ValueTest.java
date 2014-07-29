package lattelogo.lang;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ValueTest {

    @Test
    public void isNumber_NormalUse_ShouldPass() {

        Value a = new Value(1.0);
        Value b = new Value(1L);
        Value c = new Value(1.0000000001);

        assertThat(a.isNumber(), is(true));
        assertThat(b.isNumber(), is(true));
        assertThat(c.isNumber(), is(true));
    }

    @Test
    public void isInt_NormalUse_ShouldPass() {

        Value a = new Value(1.0);
        Value b = new Value(1L);
        Value c = new Value(1.0000000001);

        assertThat(a.isInt(), is(true));
        assertThat(b.isInt(), is(true));
        assertThat(c.isInt(), is(false));
    }

    @Test
    public void isFloat_NormalUse_ShouldPass() {

        Value a = new Value(1.0);
        Value b = new Value(1L);
        Value c = new Value(1.0000000001);

        assertThat(a.isFloat(), is(false));
        assertThat(b.isFloat(), is(false));
        assertThat(c.isFloat(), is(true));
    }
}
