package exceptions;

public class FieldsExceededException extends RuntimeException {

    public FieldsExceededException() {}

    public FieldsExceededException(String message) {
        super(message);
    }
}
