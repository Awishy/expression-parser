package expression.exceptions;

public class BaseParser {
    protected static final char END = '\0';
    protected char ch = 0xffff;
    protected String source;
    protected int pos;

    protected char take() {
        char result = ch;
        ch = hasNext() ? next() : END;
        return result;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean test(String expected) {
        if (pos + expected.length() > source.length()) {
            return false;
        }
        for (int i = 0; i < expected.length(); ++i) {
            if (source.charAt(pos + i - 1) != expected.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected boolean take(final String expected) {
        if (test(expected)) {
            for (int i = 0; i < expected.length(); ++i) {
                take();
            }
            return true;
        }
        return false;
    }

    protected boolean hasNext() {
        return pos < source.length();
    }

    protected char next() {
        return source.charAt(pos++);
    }

    protected void expect(final char expected) throws Exception {
        skipWhitespaces();
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected void expect(final String expected) throws Exception {
        skipWhitespaces();
        for (int i = 0; i < expected.length(); ++i) {
            if (!take(expected.charAt(i))) {
                throw error("Expected '" + expected + "', found '" + ch + "'");
            }
        }
    }

    protected void skipWhitespaces() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }

    protected boolean eof() {
        return take(END);
    }

    protected ParserException error(final String message) throws Exception {
        return new ParserException(message + " at " + pos);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}
