package expression.exceptions;

import expression.base.AbstractElement;
import expression.base.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(AbstractElement first, AbstractElement second) {
        super(first, second);
    }

    @Override
    public int evaluateImpl(int x, int y, int z) {
        if (firstRes > 0 && (secondRes > Integer.MAX_VALUE / firstRes || secondRes < Integer.MIN_VALUE / firstRes)
                || firstRes == -1 && secondRes == Integer.MIN_VALUE
                || firstRes < -1
                && (secondRes < Integer.MAX_VALUE / firstRes || secondRes > Integer.MIN_VALUE / firstRes)) {
            throw new OverflowException(firstRes, secondRes, getOperator());
        }
        return firstRes * secondRes;
    }
}
