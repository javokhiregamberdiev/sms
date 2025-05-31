package uz.student.sms.dto.student;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import uz.student.sms.dto.ItemDTO;
import uz.student.sms.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDetailDTO implements Serializable {

    Long id;

    String firstName;

    String lastName;

    String email;

    String phone;

    LocalDate dateOfBirth;

    String username;

    Gender gender;

    ItemDTO createdBy;

    String cardId;
}
