package expression.generic.operations;

public class IntegerFormat implements NumberFormat<Integer> {
    @Override
    public Integer add(Integer x, Integer y) {
        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        return x - y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        return x / y;
    }

    @Override
    public Integer minus(Integer x) {
        return -x;
    }

    @Override
    public Integer valueOf(int x) {
        return x;
    }

    @Override
    public Integer min(Integer x, Integer y) {
        return x < y ? x : y;
    }

    @Override
    public Integer max(Integer x, Integer y) {
        return x > y ? x : y;
    }

    @Override
    public Integer count(Integer x) {
        return Integer.bitCount(x);
    }
}
