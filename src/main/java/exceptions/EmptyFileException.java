package exceptions;

public class EmptyFileException extends IllegalArgumentException {
    public EmptyFileException(String message) {
        super(message);
    }
}
