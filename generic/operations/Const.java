package expression.generic.operations;

public class Const<T> extends AbstractElement<T> {
    final T value;

    public Const(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
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
        return value;
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
