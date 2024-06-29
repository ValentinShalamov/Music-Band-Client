package validator;

public class ValidationResult {
    private final boolean isCorrectData;
    private final String message;

    public ValidationResult(boolean isCorrectData,String message) {
        this.isCorrectData = isCorrectData;
        this.message = message;
    }

    public boolean isCorrectData() {
        return isCorrectData;
    }

    public String getMessage() {
        return message;
    }
}
