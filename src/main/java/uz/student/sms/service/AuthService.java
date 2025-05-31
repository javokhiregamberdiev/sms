package uz.student.sms.service;

import uz.student.sms.dto.auth.AuthTokenDTO;
import uz.student.sms.dto.auth.LoginDTO;

public interface AuthService {

    AuthTokenDTO login(LoginDTO loginDTO);

    void logout();
}
