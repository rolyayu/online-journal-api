package by.vsu.journal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public final ApiError handelRecordNotFound(RecordNotFoundException exception) {
        List<String> details = List.of(exception.getMessage());
        LocalDateTime now = LocalDateTime.now();
        String message = "Record not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ApiError.builder()
                .date(now)
                .status(status)
                .message(message)
                .details(details)
                .build();
    }

}
