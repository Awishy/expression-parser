package expression.exceptions;

public class PowException extends ArithmeticException{
    public PowException(int first, int second, String operation) {
        super("Power exception: " + first + " " + operation + " " + second);
    }
}
