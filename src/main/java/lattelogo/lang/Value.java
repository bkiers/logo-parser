package lattelogo.lang;

public class Value {

    public static final Value NOTHING = new Value(null);

    public final Object value;

    public Value(Object value) {
        this.value = value;
    }

    public double asDouble() {
        return Double.valueOf(String.valueOf(value));
    }

    public long asLong() {
        return (long) this.asDouble();
    }

    public String asString() {
        return String.valueOf(value);
    }

    public boolean isFloat() {
        return this.isNumber() && (this.asDouble() != (double) this.asLong());
    }

    public boolean isInt() {
        return this.isNumber() && !this.isFloat();
    }

    public boolean isNumber() {
        return (value != null) && (value instanceof Number);
    }

    @Override
    public String toString() {

        if (this == NOTHING) {
            return "<nothing>";
        }

        return String.valueOf(value);
    }
}
