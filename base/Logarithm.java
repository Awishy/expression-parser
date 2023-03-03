package expression.base;

public class Logarithm extends AbstractOperation {
    public Logarithm(AbstractElement first, AbstractElement second) {
        super(first, second);
    }

    @Override
    protected String getOperator() {
        return "//";
    }

    @Override
    public int evaluate(int varValue) {
        return (int) (Math.log(first.evaluate(varValue)) / Math.log(second.evaluate(varValue)));
    }

    @Override
    public int evaluateImpl(int x, int y, int z) {
        int res = 0;
        while (firstRes >= secondRes) {
            ++res;
            firstRes /= secondRes;
        }
        return res;
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
