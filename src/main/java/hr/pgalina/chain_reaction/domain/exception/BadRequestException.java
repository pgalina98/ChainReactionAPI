package hr.pgalina.chain_reaction.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class BadRequestException extends RuntimeException {

    private String errorType;

    private HttpStatus httpStatus;

    private LocalDateTime timestamp;

    public BadRequestException() {}

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String errorType, HttpStatus httpStatus, String message) {
        super(message);
        this.errorType = errorType;
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
    }
}
