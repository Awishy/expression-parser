package expression.generic.operations;

public class Multiply<T> extends AbstractOperation<T> {
    public Multiply(AbstractElement<T> first, AbstractElement<T> second, NumberFormat<T> format) {
        super(first, second, format);
    }

    @Override
    protected String getOperator() {
        return "*";
    }

    @Override
    public T evaluateImpl(T x, T y, T z) {
        return format.multiply(firstRes, secondRes);
    }

    @Override
    public int getPriority() {
        return priority();
    }

    public static int priority() {
        return 2;
    }
}
