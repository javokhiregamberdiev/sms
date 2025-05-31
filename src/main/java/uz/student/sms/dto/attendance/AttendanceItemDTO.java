package uz.student.sms.dto.attendance;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class AttendanceItemDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 12222211L;

    private int day;

    private LocalDateTime date;

    private String status;
}
