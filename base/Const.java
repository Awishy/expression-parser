package expression.base;

public class Const extends AbstractElement {
    final Number value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
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
        return value.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    protected void initializeToMiniStringBuilder(StringBuilder sb) {
        sb.append(value);
    }

    @Override
    protected void initializeStringBuilder(StringBuilder sb) {
        initializeToMiniStringBuilder(sb);
    }
}
