package validator;

public record ValidationResult(String errorMessage) {
    public static final ValidationResult OK = new ValidationResult(null);

    public boolean isValid() {
        return errorMessage == null;
    }
}
