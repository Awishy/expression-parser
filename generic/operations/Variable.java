package expression.generic.operations;

public class Variable<T> extends AbstractElement<T> {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String toMiniString() {
        return toString();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public T evaluate(T x, T y, T z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            default:
                return z;
        }
    }

    @Override
    protected void initializeToMiniStringBuilder(StringBuilder sb) {
        sb.append(name);
    }

    @Override
    protected void initializeStringBuilder(StringBuilder sb) {
        initializeToMiniStringBuilder(sb);
    }
}
