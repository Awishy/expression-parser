package expression.exceptions;

public class DBZException extends ArithmeticException{
    public DBZException(int first, int second, String operation) {
        super("Division by zero: " + first + " " + operation + " " + second);
    }

    public DBZException() {
        super("Division by zero");
    }
}
