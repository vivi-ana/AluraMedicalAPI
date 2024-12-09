package med.voll.api.domain;

/**
 * Generic exception thrown when a validation fails.
 */
public class ValidatorException extends RuntimeException {
    /**
     * Constructs a new ValidatorException with the specified detail message.
     * @param message the detail message
     */
    public ValidatorException(String message) {
        super(message);
    }
}