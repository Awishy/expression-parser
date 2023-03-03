package expression.exceptions;

public class LogException extends ArithmeticException{
    public LogException(int first, int second, String operation) {
        super("Logarithm arguments exception: " + first + " " + operation + " " + second);
    }
}
