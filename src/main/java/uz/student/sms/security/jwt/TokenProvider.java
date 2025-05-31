package uz.student.sms.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uz.student.sms.security.CustomUser;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenProvider implements InitializingBean {

    private SecretKey key;

    @Override
    public void afterPropertiesSet() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode("qwerweervwertwerte01234567890123456789012345678901"));
    }


    @Transactional(readOnly = true)
    public String createToken(Authentication authentication, boolean rememberMe) {

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + 259200 * 1000);
        } else {
            validity = new Date(now + 86400 * 1000);
        }
        Long userId = null;
        String username = null;
        String phone = null;

        if (authentication.getPrincipal() instanceof CustomUser customUser) {
            userId = customUser.getId();
            username = customUser.getUsername();
            phone = customUser.getPhone();
        }

        return Jwts.builder()
                .subject(authentication.getName())
                .claim("authorities", authorities)
                .claim("userId", userId)
                .claim("username", username)
                .claim("phone", phone)
                .signWith(key)
                .expiration(validity)
                .compact();
    }

    public Authentication getAuthentication(final String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("authorities").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();


        Long userId = claims.get("userId") != null ? Long.valueOf((Integer) claims.get("userId")) : null;
        String phone = claims.get("phone") != null ? claims.get("phone").toString() : null;
        CustomUser principal = new CustomUser(claims.getSubject(), "", authorities, userId, phone);
        return new UsernamePasswordAuthenticationToken(principal, token, Collections.emptyList());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.trace("Invalid JWT token trace.", e);
        }
        return false;
    }

}
