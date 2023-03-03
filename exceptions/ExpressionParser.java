package expression.exceptions;

import expression.base.*;

import java.util.Map;

public class ExpressionParser extends BaseParser implements TripleParser {
    private static final Map<String, Integer> PRIORITY_BY_OPERATOR = Map.of(
            "+", Add.priority(),
            "-", Subtract.priority(),
            "*", Multiply.priority(),
            "/", Divide.priority(),
            "min", Min.priority(),
            "max", Max.priority(),
            "//", Logarithm.priority(),
            "**", Pow.priority()
    );

    @Override
    public TripleExpression parse(final String expression) throws Exception {
        this.source = expression + END;
        pos = 0;
        take();
        TripleExpression result = parseExpression();
        if (eof()) {
            return result;
        }
        throw error("Expected end of source`, found: '" + ch + "'");
    }

    protected int getPriority(String operator) {
        return PRIORITY_BY_OPERATOR.getOrDefault(operator, Integer.MAX_VALUE);
    }

    protected AbstractElement parseExpression(AbstractElement first, int prevPriority) throws Exception {
        skipWhitespaces();
        while (hasNext() && ch != ')') {
            int thisPriority = getPriority(nextOperation());
            if (prevPriority >= thisPriority) {
                return first;
            }
            String operation = parseOperation();
            AbstractElement second = parseElement();
            String secondOperation = nextOperation();
            if (thisPriority < getPriority(secondOperation)) {
                second = parseExpression(second, getPriority(operation));
            }
            first = makeElement(first, second, operation);
        }
        return first;
    }

    protected AbstractElement parseExpression() throws Exception {
        return parseExpression(parseElement(), Integer.MIN_VALUE);
    }

    protected String parseOperation() {
        String operation = nextOperation();
        pos += operation.length() - 1;
        take();
        return operation;
    }

    protected String nextOperation() {
        String res = "";
        skipWhitespaces();
        for (Map.Entry<String, Integer> entry : PRIORITY_BY_OPERATOR.entrySet()) {
            String key = entry.getKey();
            if (test(key) && key.length() > res.length()) {
                res = key;
            }
        }
        return res;
    }

    protected AbstractElement makeElement(AbstractElement first, AbstractElement second, String operation)
            throws Exception {
        switch (operation) {
            case "+":
                return new CheckedAdd(first, second);
            case "-":
                return new CheckedSubtract(first, second);
            case "/":
                return new CheckedDivide(first, second);
            case "*":
                return new CheckedMultiply(first, second);
            case "min":
                return new Min(first, second);
            case "max":
                return new Max(first, second);
            case "//":
                return new CheckedLog(first, second);
            case "**":
                return new CheckedPow(first, second);
            default:
                throw error("Expected operation, found '" + operation + "'");
        }
    }

    protected AbstractElement parseElement() throws Exception {
        skipWhitespaces();
        if (take('(')) {
            AbstractElement result = parseExpression();
            expect(')');
            return result;
        } else if (take('-')) {
            return parseMinus();
        } else if (isInteger()) {
            return parseNumber(false);
        } else if (take('l')) {
            expect('0');
            return new LeadingZeros(parseElement());
        } else if (take('t')) {
            expect('0');
            return new TrailingZeros(parseElement());
        } else if (take('a')) {
            expect("bs");
            if (!test(' ') && !test('(')) {
                throw error("Expected abs, found: 'abs" + ch + "'");
            }
            return new CheckedAbs(parseElement());
        } else {
            return parseVariable();
        }
    }

    protected AbstractElement parseVariable() throws Exception {
        char name = take();
        if (name != 'x' && name != 'y' && name != 'z') {
            --pos;
            throw error("Expected variable, found: '" + name + "'");
        }
        return new Variable(Character.toString(name));
    }

    protected boolean isInteger() {
        return between('0', '9');
    }

    protected AbstractElement parseNumber(boolean isNegative) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (isNegative) {
            sb.append("-");
        }
        do {
            sb.append(take());
        } while (isInteger());
        if (Character.isLetter(ch)) {
            throw error("Expected digit, found: " + ch);
        }
        return new Const(Integer.parseInt(sb.toString()));
    }

    protected AbstractElement parseMinus() throws Exception {
        AbstractElement result;
        if (isInteger()) {
            result = parseNumber(true);
        } else {
            skipWhitespaces();
            if (take('(')) {
                result = new CheckedNegate(parseExpression());
                expect(')');
            } else if (take('-')) {
                result = new CheckedNegate(parseMinus());
            } else if (isInteger()) {
                result = new CheckedNegate(parseNumber(false));
            } else {
                result = new CheckedNegate(parseElement());
            }
        }
        return result;
    }
}