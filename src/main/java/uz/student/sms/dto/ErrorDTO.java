package uz.student.sms.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class ErrorDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 33333222L;

    private String message;

    private Map<String, String> errors;

    private String status;
}
