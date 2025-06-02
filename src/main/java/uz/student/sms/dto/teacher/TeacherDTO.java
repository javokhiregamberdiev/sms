package uz.student.sms.dto.teacher;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import uz.student.sms.dto.UserDTO;
import uz.student.sms.enums.Gender;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class TeacherDTO extends UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 23411L;

    @NotNull
    private String speciality;

    private Gender gender;

    private LocalDate dateOfBirth;
}
