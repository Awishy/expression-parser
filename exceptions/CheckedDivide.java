package expression.exceptions;

import expression.base.AbstractElement;
import expression.base.Divide;

public class CheckedDivide extends Divide {
    public CheckedDivide(AbstractElement first, AbstractElement second) {
        super(first, second);
    }

    @Override
    public int evaluateImpl(int x, int y, int z) {
        if (firstRes == Integer.MIN_VALUE && secondRes == -1) {
            throw new OverflowException(firstRes, secondRes, getOperator());
        }
        if (secondRes == 0) {
            throw new DBZException(firstRes, secondRes, getOperator());
        }
        return firstRes / secondRes;
    }
}
