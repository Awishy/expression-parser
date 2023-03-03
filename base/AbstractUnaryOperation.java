package expression.base;

public abstract class AbstractUnaryOperation extends AbstractElement{
    protected AbstractElement expression;

    public AbstractUnaryOperation(AbstractElement expression) {
        this.expression = expression;
    }

    @Override
    protected void initializeToMiniStringBuilder(StringBuilder sb) {
        if(expression.getPriority() < getPriority()) {
            sb.append(getOperator()).append("(");
            expression.initializeToMiniStringBuilder(sb);
            sb.append(")");
        } else {
            sb.append(getOperator()).append(" ");
            expression.initializeToMiniStringBuilder(sb);
        }
    }

    @Override
    protected void initializeStringBuilder(StringBuilder sb) {
        sb.append(getOperator()).append("(");
        expression.initializeStringBuilder(sb);
        sb.append(")");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        initializeStringBuilder(sb);
        return sb.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        initializeToMiniStringBuilder(sb);
        return sb.toString();
    }
}
