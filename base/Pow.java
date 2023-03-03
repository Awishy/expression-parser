package expression.base;

public class Pow extends AbstractOperation {
    public Pow(AbstractElement first, AbstractElement second) {
        super(first, second);
    }

    @Override
    protected String getOperator() {
        return "**";
    }

    @Override
    public int evaluate(int varValue) {
        return (int) (Math.pow(first.evaluate(varValue), second.evaluate(varValue)));
    }

    @Override
    public int evaluateImpl(int x, int y, int z) {
        return (int) (Math.pow(firstRes, secondRes));
    }

    @Override
    protected boolean dependsOnNumberFormat() {
        return true;
    }

    @Override
    public int getPriority() {
        return priority();
    }

    public static int priority() {
        return 10;
    }
}
