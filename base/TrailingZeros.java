package expression.base;

public class TrailingZeros extends AbstractUnaryOperation {
    public TrailingZeros(AbstractElement expression) {
        super(expression);
    }

    @Override
    public String getOperator() {
        return "t0";
    }

    @Override
    public int evaluate(int x) {
        return Integer.numberOfTrailingZeros(expression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Integer.numberOfTrailingZeros(expression.evaluate(x, y, z));
    }
}
