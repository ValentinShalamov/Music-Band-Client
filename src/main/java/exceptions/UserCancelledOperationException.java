package exceptions;

public class UserCancelledOperationException extends RuntimeException {
    public UserCancelledOperationException(String message) {
        super(message);
    }
}
