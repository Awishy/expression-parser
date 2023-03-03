package expression.base;

public class Min extends AbstractOperation {
    public Min(AbstractElement first, AbstractElement second) {
        super(first, second);
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
    public int evaluate(int varValue) {
        return Math.min(first.evaluate(varValue), second.evaluate(varValue));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Math.min(first.evaluate(x, y ,z), second.evaluate(x, y, z));
    }

    @Override
    public int getPriority() {
        return priority();
    }

    public static int priority() {
        return 0;
    }
}
