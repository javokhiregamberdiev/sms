package uz.student.sms.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {

    String message;

    HttpStatus status;

    public NotFoundException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public NotFoundException(String message) {
        super(message);
        this.message = message;
        this.status = HttpStatus.NOT_FOUND;
    }
}
