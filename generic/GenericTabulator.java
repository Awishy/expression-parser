package expression.generic;

import expression.generic.operations.*;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression,
                                 int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        NumberFormat<?> format = null;
        switch (mode) {
            case "i":
                format = new CheckedIntegerFormat();
                break;
            case "d":
                format = new DoubleFormat();
                break;
            case "bi":
                format = new BigIntegerFormat();
                break;
            case "u":
                format = new IntegerFormat();
                break;
            case "l":
                format = new LongFormat();
                break;
            case "f":
                format = new FloatFormat();
                break;
        }
        return tabulateImpl(format, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] tabulateImpl(NumberFormat<T> format, String expression,
                                          int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        ExpressionParser<T> parser = new ExpressionParser<>(format);
        GenericExpression<T> genericExpression = parser.parse(expression);
        for (int i = x1; i <= x2; ++i) {
            for (int j = y1; j <= y2; ++j) {
                for (int k = z1; k <= z2; ++k) {
                    try {
                        result[i - x1][j - y1][k - z1] = genericExpression.evaluate(format.valueOf(i),
                                format.valueOf(j), format.valueOf(k));
                    } catch (ArithmeticException e) {
                        // ignore
                    }
                }
            }
        }
        return result;
    }
}
