package uz.student.sms.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.student.sms.dto.ErrorDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> genericExceptionHandler(Exception ex, HttpServletRequest request) {
        if (StringUtils.isEmpty(ex.getMessage())) {
            Throwable throwable = ex.getCause();
            /*if (throwable instanceof AppGlobalException) {
                return this.getMessage(throwable, request);
            }*/
        }
        return get(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private  ResponseEntity<ErrorDTO> get(String message, HttpStatus status) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(message);
        errorDTO.setStatus(status.name());
        return new ResponseEntity<>(errorDTO,  status);
    }

}
