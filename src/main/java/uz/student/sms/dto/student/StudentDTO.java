package uz.student.sms.dto.student;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import uz.student.sms.dto.UserDTO;
import uz.student.sms.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDTO extends UserDTO implements Serializable {

    Gender gender;

    LocalDate dateOfBirth;

    List<Long> courseIds;

    List<Long> groupIds;

    @NotNull
    String cardId;
}
