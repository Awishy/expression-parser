package expression.generic.operations;

public class Subtract<T> extends AbstractOperation<T> {
    public Subtract(AbstractElement<T> first, AbstractElement<T> second, NumberFormat<T> format) {
        super(first, second, format);
    }

    @Override
    protected boolean requiresBrackets() {
        return true;
    }

    @Override
    protected String getOperator() {
        return "-";
    }

    @Override
    public T evaluateImpl(T x, T y, T z) {
        return format.subtract(firstRes, secondRes);
    }

    @Override
    public int getPriority() {
        return priority();
    }

    public static int priority() {
        return 1;
    }
}
