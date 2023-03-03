package expression.exceptions;

import expression.base.AbstractElement;
import expression.base.Pow;

public class CheckedPow extends Pow {
    public CheckedPow(AbstractElement first, AbstractElement second) {
        super(first, second);
    }

    @Override
    public int evaluateImpl(int x, int y, int z) {
        if (firstRes == 0 && secondRes == 0 || secondRes < 0) {
            throw new PowException(firstRes, secondRes, getOperator());
        }
        if (firstRes == 0) {
            return 0;
        }
        if (firstRes == 1) {
            return 1;
        }
        if (firstRes == -1) {
            return secondRes % 2 == 1 ? -1 : 1;
        }
        int res = 1;
        int multiplier = firstRes, degree = secondRes;
        while (degree > 0) {
            if (degree % 2 == 1) {
                if (checkOverflow(multiplier, res)) {
                    throw new OverflowException(firstRes, secondRes, getOperator());
                }
                res *= multiplier;
            }
            if (degree - 1 > 0 && checkOverflow(multiplier, multiplier)) {
                throw new OverflowException(firstRes, firstRes, getOperator());
            }
            multiplier *= multiplier;
            degree /= 2;
        }
        return res;
    }

    private boolean checkOverflow(int a, int b) {
        return (a > 0 && (b > Integer.MAX_VALUE / a || b < Integer.MIN_VALUE / a)
                || a < -1 && (b < Integer.MAX_VALUE / a || b > Integer.MIN_VALUE / a));
    }
}
