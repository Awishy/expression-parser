package expression.generic.operations;

public abstract class AbstractUnaryOperation<T> extends AbstractElement<T> {
    protected AbstractElement<T> expression;
    protected T result;

    public AbstractUnaryOperation(AbstractElement<T> expression, NumberFormat<T> format) {
        this.expression = expression;
        this.format = format;
    }

    @Override
    protected void initializeToMiniStringBuilder(StringBuilder sb) {
        if (expression.getPriority() < getPriority()) {
            sb.append(getOperator()).append("(");
            expression.initializeToMiniStringBuilder(sb);
            sb.append(")");
        } else {
            sb.append(getOperator()).append(" ");
            expression.initializeToMiniStringBuilder(sb);
        }
    }

    @Override
    protected void initializeStringBuilder(StringBuilder sb) {
        sb.append(getOperator()).append("(");
        expression.initializeStringBuilder(sb);
        sb.append(")");
    }

    @Override
    public T evaluate(T x, T y, T z) {
        result = expression.evaluate(x, y, z);
        return evaluateImpl(x, y, z);
    }

    protected T evaluateImpl(T x, T y, T z) {
        return evaluate(x, y, z);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        initializeStringBuilder(sb);
        return sb.toString();
    }

    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        initializeToMiniStringBuilder(sb);
        return sb.toString();
    }
}
