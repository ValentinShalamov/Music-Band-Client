package validator;

public class ValidationResult {
    private final String errorMessage;
    public static final ValidationResult OK = new ValidationResult(null);

    public ValidationResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isValid() {
        return errorMessage == null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
