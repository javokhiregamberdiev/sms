package uz.student.sms.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomUser extends User {

    private Long id;
    private String username;
    private String phone;

    public CustomUser(String username,
                      String password,
                      Collection<? extends GrantedAuthority> authorities,
                      Long id,
                      String phone) {
        super(username, password, authorities);
        this.id = id;
        this.username = username;
        this.phone = phone;
    }
}
