package expression.generic.operations;

public class Minus<T> extends AbstractUnaryOperation<T> {
    public Minus(AbstractElement<T> expression, NumberFormat<T> format) {
        super(expression, format);
    }

    @Override
    public String getOperator() {
        return "-";
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return format.minus(expression.evaluate(x, y, z));
    }
}
