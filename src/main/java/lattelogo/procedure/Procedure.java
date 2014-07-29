package lattelogo.procedure;

import lattelogo.lang.Value;
import lattelogo.node.Scope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Procedure {

    private static final Map<String, Procedure> PROCEDURES = new HashMap<>();

    static {
        put(new MakeProcedure());
        put(new PrintProcedure());
    }

    protected final String[] names;
    protected final int totalParams;

    protected Procedure(String name) {
        this(new String[]{name});
    }

    protected Procedure(String name, int totalParams) {
        this(new String[]{name}, totalParams);
    }

    protected Procedure(String[] names) {
        this(names, 0);
    }

    protected Procedure(String[] names, int totalParams) {
        this.names = names;
        this.totalParams = totalParams;
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
