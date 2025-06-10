package uz.student.sms.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.student.sms.domain.User;
import uz.student.sms.dto.UserDTO;
import uz.student.sms.exceptions.BadRequestException;
import uz.student.sms.repository.RoleRepository;
import uz.student.sms.repository.UserRepository;
import uz.student.sms.service.UserService;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Override
    public Long create(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        if (userDTO.getPassword() == null) {
            throw new BadRequestException("Password is required");
        }
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPhone(userDTO.getPhone());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        if (userDTO.getRoleIds() != null && !userDTO.getRoleIds().isEmpty()) {
            user.setRoles(roleRepository.findByIdIn(userDTO.getRoleIds()));
        }
        return userRepository.save(user).getId();
    }

    @Override
    public void update(Long userId, UserDTO userDTO) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setUsername(userDTO.getUsername());
            if (userDTO.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            user.setPhone(userDTO.getPhone());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            if (userDTO.getRoleIds() != null && !userDTO.getRoleIds().isEmpty()) {
                user.setRoles(roleRepository.findByIdIn(userDTO.getRoleIds()));
            }
            userRepository.save(user);
        });
    }

   /* public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }*/
}
