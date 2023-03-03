package expression.generic.operations;

public class Count<T> extends AbstractUnaryOperation<T> {
    public Count(AbstractElement<T> expression, NumberFormat<T> format) {
        super(expression, format);
    }

    @Override
    public String getOperator() {
        return "count";
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return format.count(expression.evaluate(x, y, z));
    }
}
