package expression.base;

public class Minus extends AbstractUnaryOperation {
    public Minus(AbstractElement expression) {
        super(expression);
    }

    @Override
    public String getOperator() {
        return "-";
    }

    @Override
    public int evaluate(int x) {
        return -x;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -expression.evaluate(x, y, z);
    }
}
