package uz.student.sms.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class LoginDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private String username;

    @NotNull
    private String password;

    boolean rememberMe;
}
