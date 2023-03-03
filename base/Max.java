package expression.base;

public class Max extends AbstractOperation {
    public Max(AbstractElement first, AbstractElement second) {
        super(first, second);
    }

    @Override
    protected String getOperator() {
        return "max";
    }

    @Override
    public int evaluate(int varValue) {
        return Math.max(first.evaluate(varValue), second.evaluate(varValue));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Math.max(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public int getPriority() {
        return priority();
    }

    public static int priority() {
        return 0;
    }
}
