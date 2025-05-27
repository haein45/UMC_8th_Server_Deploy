package umc.spring.apiPayload.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import umc.spring.apiPayload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationError(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(
                ApiResponse.onFailure("VALID400", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), null)
        );
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<?>> handleCustom(CustomException ex) {
        return ResponseEntity.badRequest().body(
                ApiResponse.onFailure("CUSTOM400", ex.getMessage(), null)
        );
    }
}
