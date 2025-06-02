package umc.spring.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    ErrorReasonDTO getReason();
    ErrorReasonDTO getReasonHttpStatus();

    HttpStatus getHttpStatus();
    String getMessage();
    String getCode();
}
