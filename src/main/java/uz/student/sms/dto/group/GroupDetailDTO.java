package uz.student.sms.dto.group;

import lombok.Getter;
import lombok.Setter;
import uz.student.sms.dto.ItemDTO;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class GroupDetailDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private ItemDTO teacher;

    private LocalDateTime createdDate;

    private ItemDTO createdBy;

    private ItemDTO course;
}
