package lattelogo.procedure;

import lattelogo.lang.LogoCollection;
import lattelogo.lang.Value;
import lattelogo.node.Scope;

/**
 * <pre>
 * <code>
 * ITEM index thing
 *
 *     if the "thing" is a word, outputs the "index"th character of the
 *     word.  If the "thing" is a list, outputs the "index"th member of
 *     the list.  If the "thing" is an array, outputs the "index"th
 *     member of the array.  "Index" starts at 1 for words and lists;
 *     the starting index of an array is specified when the array is
 *     created.
 * </code>
 * </pre>
 */
public class Item extends Procedure {

    protected Item() {
        super("item", 2, false);
    }

    @Override
    public Value invoke(Scope scope, Value... params) {

        super.checkParams(params);

        Value index = params[0];
        Value value = params[1];

        if (!index.isInt()) {
            throw new RuntimeException("item doesn't like " + index + " as input");
        }

        try {
            if (value.isCollection()) {
                return value.asCollection().get((int)index.asLong());
            }
            else {
                return new Value(String.valueOf(String.valueOf(value).charAt((int)index.asLong() - 1)));
            }
        }
        catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("item doesn't like " + index + " as input");
        }
    }
}
