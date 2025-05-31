package uz.student.sms.dto.course;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class CourseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 199L;

    private String name;

    private String description;
}
