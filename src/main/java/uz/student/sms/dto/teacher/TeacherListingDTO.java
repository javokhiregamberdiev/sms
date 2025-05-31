package uz.student.sms.dto.teacher;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherListingDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    Long id;

    String speciality;

    LocalDateTime createdDate;

    String firstName;

    String lastName;

    String email;

    String phone;
}
