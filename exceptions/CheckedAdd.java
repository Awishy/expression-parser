package expression.exceptions;

import expression.base.AbstractElement;
import expression.base.Add;

public class CheckedAdd extends Add {
    public CheckedAdd(AbstractElement first, AbstractElement second) {
        super(first, second);
    }

    @Override
    public int evaluateImpl(int x, int y, int z) {
        if (firstRes > 0 && Integer.MAX_VALUE - firstRes < secondRes
                || firstRes < 0 && Integer.MIN_VALUE - firstRes > secondRes) {
            throw new OverflowException(firstRes, secondRes, getOperator());
        }
        return firstRes + secondRes;
    }
}
