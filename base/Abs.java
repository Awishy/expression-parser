package expression.base;

public class Abs extends AbstractUnaryOperation {
    public Abs(AbstractElement expression) {
        super(expression);
    }

    @Override
    public String getOperator() {
        return "abs";
    }

    @Override
    public int evaluate(int x) {
        return x < 0 ? -x : x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int result = expression.evaluate(x, y, z);
        return result < 0 ? -result : result;
    }
}
