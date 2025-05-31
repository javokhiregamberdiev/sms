package uz.student.sms.resource;

import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import uz.student.sms.dto.attendance.AttendanceDTO;
import uz.student.sms.dto.attendance.AttendanceListDTO;
import uz.student.sms.dto.filter.AttendanceFilter;
import uz.student.sms.service.AttendanceService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/attendance")
public class AttendanceResource {

    private final AttendanceService attendanceService;

    @PostMapping
    public Long create(@RequestBody AttendanceDTO attendance) {
        return attendanceService.createForAdmin(attendance);
    }

    @PostMapping("/by-card")
    public Long createByCard(@RequestParam String cardId) {
        return attendanceService.create(cardId);
    }

    @GetMapping
    public List<AttendanceListDTO> getList(@ParameterObject AttendanceFilter filter) {
        return attendanceService.getList(filter);
    }
}
