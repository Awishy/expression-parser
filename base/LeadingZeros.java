package expression.base;

public class LeadingZeros extends AbstractUnaryOperation {
    public LeadingZeros(AbstractElement expression) {
        super(expression);
    }

    @Override
    public String getOperator() {
        return "l0";
    }

    @Override
    public int evaluate(int varValue) {
        return Integer.numberOfLeadingZeros(expression.evaluate(varValue));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Integer.numberOfLeadingZeros(expression.evaluate(x, y, z));
    }

    @Override
    public int getPriority() {
        return priority();
    }

    public static int priority() {
        return 10;
    }
}
