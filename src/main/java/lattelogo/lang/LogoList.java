package lattelogo.lang;

import java.util.ArrayList;
import java.util.List;

public class LogoList implements LogoCollection {

    private final List<Value> values;

    public LogoList() {
        this.values = new ArrayList<>();
    }

    @Override
    public void add(Value value) {
        this.values.add(value);
    }

    @Override
    public Value get(int index) {
        return this.values.get(index - 1);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        fill(this, builder, 0);

        return builder.toString();
    }

    private void fill(LogoList list, StringBuilder builder, int nestedLists) {

        if (nestedLists > 0) {
            builder.append('[');
        }

        if (!list.values.isEmpty()) {

            builder.append(String.valueOf(list.values.get(0)));

            for (int i = 1; i < list.values.size(); i++) {

                Value value = list.values.get(i);

                builder.append(' ');

                if (value.isList()) {
                    fill(value.asList(), builder, nestedLists + 1);
                }
                else {
                    builder.append(String.valueOf(value));
                }
            }
        }

        if (nestedLists > 0) {
            builder.append(']');
        }
    }
}
