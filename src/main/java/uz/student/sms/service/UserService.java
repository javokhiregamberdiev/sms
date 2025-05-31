package uz.student.sms.service;

import uz.student.sms.dto.UserDTO;

public interface UserService {

    Long create(UserDTO userDTO);

    void update(Long userId, UserDTO userDTO);
}
