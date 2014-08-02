package lattelogo.procedure;

import lattelogo.lang.Value;
import lattelogo.node.Scope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Procedure {

    private static final Map<String, Procedure> PROCEDURES = new HashMap<>();

    static {
        put(new If());
        put(new IfElse());
        put(new Item());
        put(new Make());
        put(new Print());
        put(new Thing());
    }

    protected final String[] names;
    protected final int totalParams;
    protected final boolean extraParams;

    protected Procedure(String name, boolean extraParams) {
        this(new String[]{name}, extraParams);
    }

    protected Procedure(String name, int totalParams, boolean extraParams) {
        this(new String[]{name}, totalParams, extraParams);
    }

    protected Procedure(String[] names, boolean extraParams) {
        this(names, 0, extraParams);
    }

    protected Procedure(String[] names, int totalParams, boolean extraParams) {
        this.names = names;
        this.totalParams = totalParams;
        this.extraParams = extraParams;
    }

    protected void checkParams(Value[] params) {

        if (params.length < totalParams) {
            throw new RuntimeException("not enough inputs to " + names[0]);
        }

        if (((params.length - 1) > totalParams) && !extraParams) {
            throw new RuntimeException("too many inputs to " + names[0]);
        }
    }

    public Value invoke(Scope scope, Collection<Value> params) {
        return this.invoke(scope, params.toArray(new Value[params.size()]));
    }

    public abstract Value invoke(Scope scope, Value... params);

    public static void put(Procedure procedure) {
        for (String name : procedure.names) {
            PROCEDURES.put(name, procedure);
        }
    }

    public static Procedure get(String name) {

        Procedure procedure = PROCEDURES.get(name);

        if (procedure == null) {
            throw new RuntimeException("no such procedure: " + name);
        }

        return procedure;
    }
}
