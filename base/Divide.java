package expression.base;

public class Divide extends AbstractOperation {
    public Divide(AbstractElement first, AbstractElement second) {
        super(first, second);
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
    public int evaluate(int varValue) {
        return first.evaluate(varValue) / second.evaluate(varValue);
    }

    @Override
    public int evaluateImpl(int x, int y, int z) {
        return firstRes / secondRes;
    }

    @Override
    public int getPriority() {
        return priority();
    }

    public static int priority() {
        return 2;
    }
}
