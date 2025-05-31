package uz.student.sms.dto.attendance;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class AttendanceDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234222L;

    private String cardId;

    private LocalDateTime date;

    private Long studentId;
}
