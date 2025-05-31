package uz.student.sms.dto.teacher;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import uz.student.sms.dto.ItemDTO;
import uz.student.sms.enums.Gender;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherDetailDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    Long id;

    String firstName;

    String lastName;

    String speciality;

    String email;

    String phone;

    LocalDate dateOfBirth;

    String username;

    Gender gender;

    ItemDTO createdBy;
}
