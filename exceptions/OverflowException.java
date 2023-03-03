package expression.exceptions;

public class OverflowException extends ArithmeticException{
    public OverflowException(int first, int second, String operation) {
        super("Overflow exception: " + first + " " + operation + " " + second);
    }

    public OverflowException(int value, String operation) {
        super("Overflow exception: " + operation + value);
    }

    public OverflowException() {
        super("Overflow exception");
    }
}
