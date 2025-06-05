package uz.student.sms.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.student.sms.dto.ErrorDTO;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> genericExceptionHandler(Exception ex, HttpServletRequest request) {
        log.info(ex.toString());
        return get(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> notFoundExceptionHandler(NotFoundException ex, HttpServletRequest request) {
        log.info(ex.toString());
        return get(ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDTO> badRequestExceptionHandler(BadRequestException ex, HttpServletRequest request) {
        log.info(ex.getMessage());
        return get(ex.getMessage(), ex.getStatus());
    }

    private  ResponseEntity<ErrorDTO> get(String message, HttpStatus status) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(message);
        errorDTO.setStatus(status.name());
        return ResponseEntity.status(status).body(errorDTO);
    }

}
