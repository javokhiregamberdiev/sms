package uz.student.sms.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AuthTokenDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String token;
}
