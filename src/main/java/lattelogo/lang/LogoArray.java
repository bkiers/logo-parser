package lattelogo.lang;

import java.util.ArrayList;
import java.util.List;

public class LogoArray {

    private final List<Value> values;

    public LogoArray() {
        this.values = new ArrayList<>();
    }


    public void add(Value value) {
        this.values.add(value);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        fill(this, builder);

        return builder.toString();
    }

    private void fill(LogoArray array, StringBuilder builder) {

        builder.append('{');


        if (!array.values.isEmpty()) {

            builder.append(String.valueOf(array.values.get(0)));

            for (int i = 1; i < array.values.size(); i++) {

                Value value = array.values.get(i);

                builder.append(' ');

                if (value.isArray()) {
                    fill(value.asArray(), builder);
                }
                else {
                    builder.append(String.valueOf(value));
                }
            }
        }

        builder.append('}');
    }
}
