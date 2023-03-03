package expression.base;

public class Variable extends AbstractElement {
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

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public int evaluate(int varValue) {
        return varValue;
    }

    @Override
    public int evaluate(int x, int y, int z) {
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
