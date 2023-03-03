package expression.exceptions;

import expression.base.AbstractElement;
import expression.base.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(AbstractElement first, AbstractElement second) {
        super(first, second);
    }

    @Override
    public int evaluateImpl(int x, int y, int z) {
        if (secondRes > 0 && Integer.MIN_VALUE + secondRes > firstRes
                || secondRes <= 0 && Integer.MAX_VALUE + secondRes < firstRes) {
            throw new OverflowException(firstRes, secondRes, getOperator());
        }
        return firstRes - secondRes;
    }
}
