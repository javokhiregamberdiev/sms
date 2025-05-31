package uz.student.sms.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uz.student.sms.domain.User;
import uz.student.sms.repository.UserRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component("userDetailsService")
@AllArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);

        return userRepository.findByUsername(lowercaseLogin)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new RuntimeException("Username not found!"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new CustomUser(user.getUsername(), user.getPassword(), grantedAuthorities, user.getId(), user.getPhone());
    }

}
