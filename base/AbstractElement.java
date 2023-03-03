package expression.base;

public abstract class AbstractElement implements Expression, TripleExpression, ToMiniString{
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    protected boolean dependsOnNumberFormat() {
        return false;
    }

    protected boolean requiresBrackets() {
        return false;
    }

    protected boolean associative() {
        return true;
    }

    protected String getOperator() {
        return "";
    }

    protected abstract void initializeToMiniStringBuilder(StringBuilder sb);

    protected abstract void initializeStringBuilder(StringBuilder sb);

    @Override
    public boolean equals(Object other) {
        return other instanceof Expression && this.hashCode() == other.hashCode();
    }
}
