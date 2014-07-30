package lattelogo.lang;

public class Value {

    public static final Value NOTHING = new Value(null);
    public static final Value TRUE = new Value("true");
    public static final Value FALSE = new Value("false");

    public final Object value;
    private boolean list;

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

            if (string.matches("-?(\\d+\\.\\d*|\\.?\\d+)")) {

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

    public LogoArray asArray() {
        return (LogoArray) value;
    }

    public double asDouble() {
        return Double.valueOf(String.valueOf(value));
    }

    public LogoList asList() {
        return (LogoList) value;
    }

    public LogoCollection asCollection() {
        return (LogoCollection) value;
    }

    public long asLong() {
        return (long) this.asDouble();
    }

    public String asString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (o == this) {
            return true;
        }

        Value that = (Value) o;

        return this.value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return this.value == null ? 0 : this.value.hashCode();
    }

    public boolean isArray() {
        return (value != null) && (value instanceof LogoArray);
    }

    public boolean isCollection() {
        return this.isArray() || this.isList();
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

    public boolean isList() {
        return (value != null) && (value instanceof LogoList);
    }

    @Override
    public String toString() {

        if (this == NOTHING) {
            return "<nothing>";
        }

        return String.valueOf(value);
    }
}
