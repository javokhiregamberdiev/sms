package uz.student.sms.dto.filter;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class AttendanceFilter extends BaseFilter implements Serializable {

    @Serial
    private static final long serialVersionUID = 1432523423L;

    private int month;

    private int year;
}
