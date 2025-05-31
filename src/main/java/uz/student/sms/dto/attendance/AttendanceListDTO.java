package uz.student.sms.dto.attendance;

import lombok.Getter;
import lombok.Setter;
import uz.student.sms.dto.ItemDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class AttendanceListDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 14734L;

    private ItemDTO student;

    private List<AttendanceItemDTO> items;
}
