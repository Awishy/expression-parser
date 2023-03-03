package expression.base;

public abstract class AbstractOperation extends AbstractElement {
    final protected AbstractElement first;
    final protected AbstractElement second;
    protected String string;
    protected String miniString;
    protected Integer hash = null;
    protected int firstRes;
    protected int secondRes;

    public AbstractOperation(AbstractElement first, AbstractElement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    protected void initializeToMiniStringBuilder(StringBuilder sb) {
        if (getPriority() > first.getPriority()) {
            sb.append("(");
            first.initializeToMiniStringBuilder(sb);
            sb.append(")");
        } else {
            first.initializeToMiniStringBuilder(sb);
        }
        sb.append(" ").append(getOperator()).append(" ");
        if (getPriority() > second.getPriority()
                || getPriority() == second.getPriority() && ((requiresBrackets() || second.dependsOnNumberFormat())
                || (associative() != second.associative()))) {
            sb.append("(");
            second.initializeToMiniStringBuilder(sb);
            sb.append(")");
        } else{
            second.initializeToMiniStringBuilder(sb);
        }
    }

    @Override
    public String toMiniString() {
        if (miniString == null) {
            StringBuilder sb = new StringBuilder();
            initializeToMiniStringBuilder(sb);
            miniString = sb.toString();
        }
        return miniString;
    }

    @Override
    public int hashCode() {
        return hash == null
                ? hash = 17 * first.hashCode() + 7 * second.hashCode() + getOperator().hashCode()
                : hash;
    }

    @Override
    protected void initializeStringBuilder(StringBuilder sb) {
        sb.append("(");
        first.initializeStringBuilder(sb);
        sb.append(" ").append(getOperator()).append(" ");
        second.initializeStringBuilder(sb);
        sb.append(")");
    }

    @Override
    public int evaluate(int x, int y, int z) {
        firstRes = first.evaluate(x, y, z);
        secondRes = second.evaluate(x, y, z);
        return evaluateImpl(x, y, z);
    }

    protected int evaluateImpl(int x, int y, int z) {
        return evaluate(x, y, z);
    }


    @Override
    public String toString() {
        if (string == null) {
            StringBuilder sb = new StringBuilder();
            initializeStringBuilder(sb);
            string = sb.toString();
        }
        return string;
    }
}
