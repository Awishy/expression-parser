package expression.generic;

import expression.generic.operations.*;

import java.util.Map;

public class ExpressionParser<T> extends expression.exceptions.BaseParser {
    protected NumberFormat<T> format;

    public ExpressionParser (NumberFormat<T> format) {
        this.format = format;
    }

    private static final Map<String, Integer> PRIORITY_BY_OPERATOR = Map.of(
            "+", Add.priority(),
            "-", Subtract.priority(),
            "*", Multiply.priority(),
            "/", Divide.priority(),
            "min", Min.priority(),
            "max", Max.priority()
    );

    public GenericExpression<T> parse(final String expression) throws Exception {
        this.source = expression + END;
        pos = 0;
        take();
        GenericExpression<T> result = parseExpression();
        if (eof()) {
            return result;
        }
        throw error("Expected end of source`, found: '" + ch + "'");
    }

    protected int getPriority(String operator) {
        return PRIORITY_BY_OPERATOR.getOrDefault(operator, Integer.MAX_VALUE);
    }

    protected AbstractElement<T> parseExpression(AbstractElement<T> first, int prevPriority) throws Exception {
        skipWhitespaces();
        while (hasNext() && ch != ')') {
            int thisPriority = getPriority(nextOperation());
            if (prevPriority >= thisPriority) {
                return first;
            }
            String operation = parseOperation();
            AbstractElement<T> second = parseElement();
            String secondOperation = nextOperation();
            if (thisPriority < getPriority(secondOperation)) {
                second = parseExpression(second, getPriority(operation));
            }
            first = makeElement(first, second, operation);
        }
        return first;
    }

    protected AbstractElement<T> parseExpression() throws Exception {
        return parseExpression(parseElement(), Integer.MIN_VALUE);
    }

    protected String parseOperation() {
        String operation = nextOperation();
        pos += operation.length() - 1;
        take();
        return operation;
    }

    protected String nextOperation() {
        skipWhitespaces();
        String res = "";
        for (Map.Entry<String, Integer> entry : PRIORITY_BY_OPERATOR.entrySet()) {
            String key = entry.getKey();
            if (test(key) && key.length() > res.length()) {
                res = key;
            }
        }
        return res;
    }

    protected AbstractElement<T> makeElement(AbstractElement<T> first, AbstractElement<T> second, String operation)
            throws Exception {
        switch (operation) {
            case "+":
                return new Add<>(first, second, format);
            case "-":
                return new Subtract<>(first, second, format);
            case "/":
                return new Divide<>(first, second, format);
            case "*":
                return new Multiply<>(first, second, format);
            case "min":
                return new Min<>(first, second, format);
            case "max":
                return new Max<>(first, second, format);
            default:
                throw error("Expected operation, found '" + operation + "'");
        }
    }

    protected AbstractElement<T> parseElement() throws Exception {
        skipWhitespaces();
        if (take('(')) {
            AbstractElement<T> result = parseExpression();
            expect(')');
            return result;
        } else if (take('-')) {
            return parseMinus();
        } else if (isDigit()) {
            return parseNumber(false);
        } else if (take("count")) {
            return new Count<>(parseElement(), format);
        } else {
            return parseVariable();
        }
    }

    protected AbstractElement<T> parseVariable() throws Exception {
        char name = take();
        if (name != 'x' && name != 'y' && name != 'z') {
            --pos;
            throw error("Expected variable, found: '" + name + "'");
        }
        return new Variable<>(Character.toString(name));
    }

    protected boolean isDigit() {
        return between('0', '9');
    }

    protected AbstractElement<T> parseNumber(boolean isNegative) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (isNegative) {
            sb.append("-");
        }
        do {
            sb.append(take());
        } while (isDigit());
        if (Character.isLetter(ch)) {
            throw error("Expected digit, found: " + ch);
        }
        return new Const<>(format.valueOf(Integer.parseInt(sb.toString())));
    }

    protected AbstractElement<T> parseMinus() throws Exception {
        AbstractElement<T> result;
        if (isDigit()) {
            result = parseNumber(true);
        } else {
            skipWhitespaces();
            if (take('(')) {
                result = new Minus<>(parseExpression(), format);
                expect(')');
            } else if (take('-')) {
                result = new Minus<>(parseMinus(), format);
            } else if (isDigit()) {
                result = new Minus<>(parseNumber(false), format);
            } else {
                result = new Minus<>(parseElement(), format);
            }
        }
        return result;
    }
}