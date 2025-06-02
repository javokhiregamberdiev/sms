package uz.student.sms.dto.course;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class CourseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 199L;

    @NotNull
    private String name;

    @NotNull
    private String description;
}
