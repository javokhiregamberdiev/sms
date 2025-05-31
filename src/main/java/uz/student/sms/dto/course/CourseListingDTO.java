package uz.student.sms.dto.course;

import lombok.Getter;
import lombok.Setter;
import uz.student.sms.dto.ItemDTO;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class CourseListingDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String description;

    private LocalDateTime createdDate;

    private ItemDTO createdBy;
}
