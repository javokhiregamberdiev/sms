package uz.student.sms.repository.custom;

import uz.student.sms.domain.Attendance;
import uz.student.sms.dto.filter.AttendanceFilter;

import java.util.List;

public interface AttendanceRepositoryCustom {

    List<Attendance> getListByFilter(AttendanceFilter filter);
}
