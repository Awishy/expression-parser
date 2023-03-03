package expression.generic.operations;

public class Max<T> extends AbstractOperation<T> {
    public Max(AbstractElement<T> first, AbstractElement<T> second, NumberFormat<T> format) {
        super(first, second, format);
    }

    @Override
    protected String getOperator() {
        return "max";
    }

    @Override
    protected boolean associative() {
        return false;
    }

    @Override
    public T evaluateImpl(T x, T y, T z) {
        return format.max(firstRes, secondRes);
    }

    @Override
    public int getPriority() {
        return priority();
    }

    public static int priority() {
        return 0;
    }
}
