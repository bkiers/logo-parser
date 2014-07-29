package lattelogo.lang;

public class Value {

    public static final Value NOTHING = new Value(null);

    public final Object value;

    protected Value(Object value) {
        this.value = value;
    }

    public String asString() {
        return String.valueOf(value);
    }

    @Override
    public String toString() {

        if (this == NOTHING) {
            return "<nothing>";
        }

        return String.valueOf(value);
    }
}
