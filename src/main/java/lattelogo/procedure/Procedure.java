package lattelogo.procedure;

public abstract class Procedure {

    protected final String[] names;
    protected final int totalParams;

    public Procedure(String[] names) {
        this(names, 0);
    }

    public Procedure(String[] names, int totalParams) {
        this.names = names;
        this.totalParams = totalParams;
    }
}
