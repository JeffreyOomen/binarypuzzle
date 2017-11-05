package exceptions;

public class NoRowAvailableException extends RuntimeException {

    public NoRowAvailableException() {}

    public NoRowAvailableException(String message) {
        super(message);
    }
}
