package expression.generic.operations;

public abstract class AbstractOperation<T> extends AbstractElement<T> {
    final protected AbstractElement<T> first;
    final protected AbstractElement<T> second;
    protected String string;
    protected String miniString;
    protected Integer hash = null;
    protected T firstRes;
    protected T secondRes;

    public AbstractOperation(AbstractElement<T> first, AbstractElement<T> second, NumberFormat<T> format) {
        this.first = first;
        this.second = second;
        this.format = format;
    }

    @Override
    protected void initializeToMiniStringBuilder(StringBuilder sb) {
        if (getPriority() > first.getPriority()) {
            sb.append("(");
            first.initializeToMiniStringBuilder(sb);
            sb.append(")");
        } else {
            first.initializeToMiniStringBuilder(sb);
        }
        sb.append(" ").append(getOperator()).append(" ");
        if (getPriority() > second.getPriority()
                || getPriority() == second.getPriority() && ((requiresBrackets() || second.dependsOnNumberFormat())
                || (associative() != second.associative()))) {
            sb.append("(");
            second.initializeToMiniStringBuilder(sb);
            sb.append(")");
        } else {
            second.initializeToMiniStringBuilder(sb);
        }
    }

    public String toMiniString() {
        if (miniString == null) {
            StringBuilder sb = new StringBuilder();
            initializeToMiniStringBuilder(sb);
            miniString = sb.toString();
        }
        return miniString;
    }

    @Override
    public int hashCode() {
        return hash == null
                ? hash = 17 * first.hashCode() + 7 * second.hashCode() + getOperator().hashCode()
                : hash;
    }

    @Override
    protected void initializeStringBuilder(StringBuilder sb) {
        sb.append("(");
        first.initializeStringBuilder(sb);
        sb.append(" ").append(getOperator()).append(" ");
        second.initializeStringBuilder(sb);
        sb.append(")");
    }

    @Override
    public T evaluate(T x, T y, T z) {
        firstRes = first.evaluate(x, y, z);
        secondRes = second.evaluate(x, y, z);
        return evaluateImpl(x, y, z);
    }

    protected abstract T evaluateImpl(T x, T y, T z);

    @Override
    public String toString() {
        if (string == null) {
            StringBuilder sb = new StringBuilder();
            initializeStringBuilder(sb);
            string = sb.toString();
        }
        return string;
    }
}
