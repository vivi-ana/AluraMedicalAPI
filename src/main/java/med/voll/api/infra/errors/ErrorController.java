package med.voll.api.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global error handler using @ControllerAdvice to handle specific exceptions.
 * Provides methods to handle 404 and 400 HTTP errors.
 */
@ControllerAdvice
public class ErrorController {

    /**
     * Handles EntityNotFoundException and returns a 404 Not Found response.
     * @return the response entity with 404 Not Found status
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404 (){
        return ResponseEntity.notFound().build();
    }

    /**
     * Handles MethodArgumentNotValidException and returns a 400 Bad Request response.
     * Extracts validation errors from the exception and includes them in the response body.
     * @param e the MethodArgumentNotValidException thrown when method arguments are invalid
     * @return the response entity with 400 Bad Request status and the list of validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400 (MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(ErrorFieldValidation::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * A record that holds validation error details for a field.
     * @param field the name of the field that caused the validation error
     * @param error the validation error message
     */
    private record ErrorFieldValidation (String field, String error){

        /**
         * Constructs an ErrorFieldValidation object using a FieldError instance.
         * @param fieldError the FieldError object containing error details
         */
        private ErrorFieldValidation(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}