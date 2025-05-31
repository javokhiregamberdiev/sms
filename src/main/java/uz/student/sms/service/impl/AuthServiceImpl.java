package uz.student.sms.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.student.sms.dto.auth.AuthTokenDTO;
import uz.student.sms.dto.auth.LoginDTO;
import uz.student.sms.security.jwt.TokenProvider;
import uz.student.sms.service.AuthService;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final TokenProvider tokenProvider;

    @Override
    public AuthTokenDTO login(LoginDTO loginDTO) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication =  authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            String token = tokenProvider.createToken(authentication, loginDTO.isRememberMe());
            return new AuthTokenDTO(token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            authentication.setAuthenticated(false);
        }
    }
}
