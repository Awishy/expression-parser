package expression.generic.operations;

public class Divide<T> extends AbstractOperation<T> {
    public Divide(AbstractElement<T> first, AbstractElement<T> second, NumberFormat<T> format) {
        super(first, second, format);
    }

    @Override
    protected boolean dependsOnNumberFormat() {
        return true;
    }

    @Override
    protected boolean requiresBrackets() {
        return true;
    }

    @Override
    protected String getOperator() {
        return "/";
    }

    @Override
    public T evaluateImpl(T x, T y, T z) {
        return format.divide(firstRes, secondRes);
    }

    @Override
    public int getPriority() {
        return priority();
    }

    public static int priority() {
        return 2;
    }
}
