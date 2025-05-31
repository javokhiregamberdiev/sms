package uz.student.sms.service;

import uz.student.sms.dto.attendance.AttendanceDTO;
import uz.student.sms.dto.attendance.AttendanceListDTO;
import uz.student.sms.dto.filter.AttendanceFilter;

import java.util.List;

public interface AttendanceService {

    Long create(String cardId);

    Long createForAdmin(AttendanceDTO attendanceDTO);

    List<AttendanceListDTO> getList(AttendanceFilter filter);
}
