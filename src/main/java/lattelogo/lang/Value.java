package lattelogo.lang;

public class Value {

    public static final Value NOTHING = new Value(null);

    public final Object value;

    public Value(Object value) {
        this.value = castValue(value);
    }

    private Object castValue(Object value) {

        if (value == null) {
            return null;
        }

        if (value instanceof Double) {

            Double number = (Double) value;

            if (number % 1 == 0.0) {
                return number.longValue();
            }
        }
        else if (value instanceof String) {

            String string = (String) value;

            if (string.matches("\\d+\\.\\d*|\\.?\\d+")) {

                Double number = Double.valueOf(string);

                if (number % 1 == 0.0) {
                    return number.longValue();
                }
                else {
                    return number;
                }
            }
        }

        return value;
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
        return this.isNumber() && (value instanceof Double);
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
