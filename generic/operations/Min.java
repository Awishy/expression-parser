package expression.generic.operations;

public class Min<T> extends AbstractOperation<T> {
    public Min(AbstractElement<T> first, AbstractElement<T> second, NumberFormat<T> format) {
        super(first, second, format);
    }

    @Override
    protected String getOperator() {
        return "min";
    }

    @Override
    protected boolean associative() {
        return false;
    }

    @Override
    public T evaluateImpl(T x, T y, T z) {
        return format.min(firstRes, secondRes);
    }

    @Override
    public int getPriority() {
        return priority();
    }

    public static int priority() {
        return 0;
    }
}
