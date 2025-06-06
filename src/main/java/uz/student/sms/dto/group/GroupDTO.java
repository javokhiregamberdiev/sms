package uz.student.sms.dto.group;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class GroupDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234526L;

    @NotNull
    private String name;

    private Long teacherId;

    private Long courseId;
}
