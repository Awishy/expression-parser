package expression.generic.operations;

import expression.exceptions.DBZException;
import expression.exceptions.OverflowException;

public class CheckedIntegerFormat extends IntegerFormat {
    @Override
    public Integer add(Integer x, Integer y) {
        if (x > 0 && Integer.MAX_VALUE - x < y
                || x < 0 && Integer.MIN_VALUE - x > y) {
            throw new OverflowException();
        }
        return super.add(x, y);
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        if (y > 0 && Integer.MIN_VALUE + y > x
                || y <= 0 && Integer.MAX_VALUE + y < x) {
            throw new OverflowException();
        }
        return super.subtract(x, y);
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        if (x > 0 && (y > Integer.MAX_VALUE / x || y < Integer.MIN_VALUE / x)
                || x == -1 && y == Integer.MIN_VALUE
                || x < -1
                && (y < Integer.MAX_VALUE / x || y > Integer.MIN_VALUE / x)) {
            throw new OverflowException();
        }
        return super.multiply(x, y);
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException();
        }
        if (y == 0) {
            throw new DBZException();
        }
        return x / y;
    }

    @Override
    public Integer minus(Integer x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -x;
    }

    @Override
    public Integer valueOf(int x) {
        return x;
    }
}
