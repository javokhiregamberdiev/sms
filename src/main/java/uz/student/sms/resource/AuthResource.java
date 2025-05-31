package uz.student.sms.resource;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.student.sms.dto.auth.AuthTokenDTO;
import uz.student.sms.dto.auth.LoginDTO;
import uz.student.sms.service.AuthService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
public class AuthResource {

    private AuthService authService;

    @PostMapping("/login")
    public AuthTokenDTO login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @PostMapping("/logout")
    public void logout() {
        authService.logout();
    }
}
