package by.vsu.journal.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy mm:HH:ss")
    private LocalDateTime date;
    private HttpStatus status;
    private String message;
    private List<String> details;
}
