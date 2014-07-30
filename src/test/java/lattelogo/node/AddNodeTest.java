package lattelogo.node;

import lattelogo.lang.Value;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNodeTest {

    private AddNode create(Value lhs, Value rhs) {
        return new AddNode(new ValueNode(lhs), new ValueNode(rhs));
    }

    @Test
    public void eval_NormalUse_ShouldPass() {

        Value a = new Value(1);
        Value b = new Value(0.5);
        Value c = new Value("-100");
        Value d = new Value("42");
        Value e = new Value("-.25");

        assertThat(create(a, b).eval(null), is(new Value(1.5d)));
        assertThat(create(a, c).eval(null), is(new Value(-99L)));
        assertThat(create(a, d).eval(null), is(new Value(43L)));
        assertThat(create(a, e).eval(null), is(new Value(0.75d)));
    }
}
