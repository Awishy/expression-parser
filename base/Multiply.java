package expression.base;

public class Multiply extends AbstractOperation {
    public Multiply(AbstractElement first, AbstractElement second) {
        super(first, second);
    }

    @Override
    protected String getOperator() {
        return "*";
    }

    @Override
    public int evaluate(int varValue) {
        return first.evaluate(varValue) * second.evaluate(varValue);
    }

    @Override
    public int evaluateImpl(int x, int y, int z) {
        return first.evaluate(x, y, z) * second.evaluate(x, y, z);
    }

    @Override
    public int getPriority() {
        return priority();
    }

    public static int priority() {
        return 2;
    }
}
