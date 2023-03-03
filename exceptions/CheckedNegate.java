package expression.exceptions;

import expression.base.AbstractElement;
import expression.base.Minus;

public class CheckedNegate extends Minus {
    public CheckedNegate(AbstractElement expression) {
        super(expression);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        final int res = expression.evaluate(x, y, z);
        if (Integer.MIN_VALUE == res) {
            throw new OverflowException(res, getOperator());
        }
        return -res;
    }
}
