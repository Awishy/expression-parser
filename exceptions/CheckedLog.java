package expression.exceptions;

import expression.base.AbstractElement;
import expression.base.Logarithm;

public class CheckedLog extends Logarithm {
    public CheckedLog(AbstractElement first, AbstractElement second) {
        super(first, second);
    }

    @Override
    public int evaluateImpl(int x, int y, int z) {
        if (firstRes <= 0 || secondRes <= 0 || secondRes == 1) {
            throw new LogException(firstRes, secondRes, getOperator());
        }
        int res = 0;
        while (firstRes >= secondRes) {
            ++res;
            firstRes /= secondRes;
        }
        return res;
    }
}
