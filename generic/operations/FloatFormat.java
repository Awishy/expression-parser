package expression.generic.operations;

public class FloatFormat implements NumberFormat<Float> {
    @Override
    public Float add(Float x, Float y) {
        return x + y;
    }

    @Override
    public Float subtract(Float x, Float y) {
        return x - y;
    }

    @Override
    public Float multiply(Float x, Float y) {
        return x * y;
    }

    @Override
    public Float divide(Float x, Float y) {
        return x / y;
    }

    @Override
    public Float minus(Float x) {
        return -x;
    }

    @Override
    public Float valueOf(int x) {
        return (float) x;
    }

    @Override
    public Float min(Float x, Float y) {
        return Math.min(x, y);
    }

    @Override
    public Float max(Float x, Float y) {
        return Math.max(x, y);
    }

    @Override
    public Float count(Float x) {
        return (float) Integer.bitCount(Float.floatToIntBits(x));
    }
}
