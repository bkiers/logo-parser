package lattelogo.lang;

public class Value {

    public static final Value NOTHING = new Value(null);

    public final Object value;

    protected Value(Object value) {
        this.value = value;
    }
}
