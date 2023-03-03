package expression.generic.operations;

import expression.generic.GenericExpression;

public abstract class AbstractElement<T> implements GenericExpression<T> {
    NumberFormat<T> format;

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
        return other instanceof GenericExpression<?> && this.hashCode() == other.hashCode();
    }
}
