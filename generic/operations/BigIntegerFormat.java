package expression.generic.operations;

import java.math.BigInteger;

public class BigIntegerFormat implements NumberFormat<BigInteger> {
    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger divide(BigInteger x, BigInteger y) {
        return x.divide(y);
    }

    @Override
    public BigInteger minus(BigInteger x) {
        return x.negate();
    }

    @Override
    public BigInteger valueOf(int x) {
        return BigInteger.valueOf(x);
    }

    @Override
    public BigInteger min(BigInteger x, BigInteger y) {
        return (x.compareTo(y) < 0) ? x : y;
    }

    @Override
    public BigInteger max(BigInteger x, BigInteger y) {
        return (x.compareTo(y) > 0) ? x : y;
    }

    @Override
    public BigInteger count(BigInteger x) {
        return valueOf(x.bitCount());
    }
}
