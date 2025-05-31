package uz.student.sms.dto.student;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentListDTO implements Serializable {

    Long id;

    LocalDateTime createdDate;

    String firstName;

    String lastName;

    String email;

    String phone;
}
