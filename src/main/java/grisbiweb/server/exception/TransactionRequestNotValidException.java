package grisbiweb.server.exception;

public class TransactionRequestNotValidException extends RuntimeException {

    private static final long serialVersionUID = -3371848526650970099L;

    public TransactionRequestNotValidException(String field) {
        super(getMessage(field));
    }

    public TransactionRequestNotValidException(String field, Throwable source) {
        super(getMessage(field), source);
    }

    private static String getMessage(String field) {
        return "the field " + field + " is not valid";
    }
}
