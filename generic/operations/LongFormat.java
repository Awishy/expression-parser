package expression.generic.operations;

public class LongFormat implements NumberFormat<Long> {
    @Override
    public Long add(Long x, Long y) {
        return x + y;
    }

    @Override
    public Long subtract(Long x, Long y) {
        return x - y;
    }

    @Override
    public Long multiply(Long x, Long y) {
        return x * y;
    }

    @Override
    public Long divide(Long x, Long y) {
        return x / y;
    }

    @Override
    public Long minus(Long x) {
        return -x;
    }

    @Override
    public Long valueOf(int x) {
        return (long) x;
    }

    @Override
    public Long min(Long x, Long y) {
        return x < y ? x : y;
    }

    @Override
    public Long max(Long x, Long y) {
        return x > y ? x : y;
    }

    @Override
    public Long count(Long x) {
        return valueOf(Long.bitCount(x));
    }
}
