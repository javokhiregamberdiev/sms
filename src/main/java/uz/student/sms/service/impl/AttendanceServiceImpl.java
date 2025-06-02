package uz.student.sms.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.student.sms.domain.Attendance;
import uz.student.sms.dto.ItemDTO;
import uz.student.sms.dto.attendance.AttendanceDTO;
import uz.student.sms.dto.attendance.AttendanceItemDTO;
import uz.student.sms.dto.attendance.AttendanceListDTO;
import uz.student.sms.dto.filter.AttendanceFilter;
import uz.student.sms.repository.AttendanceRepository;
import uz.student.sms.repository.StudentRepository;
import uz.student.sms.service.AttendanceService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    @Override
    public Long create(String cardId) {
        return studentRepository.findByCardId(cardId).map(student -> {
            LocalDateTime startDate = LocalDate.now().atStartOfDay();
            LocalDateTime endDate = LocalDate.now().atTime(LocalTime.MAX);
            Attendance attendance = attendanceRepository.findFirstAttendanceOfDay(startDate, endDate, student.getUserId()).orElse(new Attendance());
            attendance.setUserId(student.getUserId());
            attendance.setCheckIn(LocalDateTime.now());
            return attendanceRepository.save(attendance).getId();
        }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public Long createForAdmin(AttendanceDTO attendanceDTO) {
        return studentRepository.findById(attendanceDTO.getStudentId()).map(student -> {
            LocalDateTime startDate = attendanceDTO.getDate() == null ? LocalDate.now().atStartOfDay() : attendanceDTO.getDate().toLocalDate().atStartOfDay();
            LocalDateTime endDate = attendanceDTO.getDate() == null ? LocalDate.now().atTime(LocalTime.MAX) : attendanceDTO.getDate().toLocalDate().atTime(LocalTime.MAX);
            Attendance attendance = attendanceRepository.findFirstAttendanceOfDay(startDate, endDate, student.getUserId()).orElse(new Attendance());
            attendance.setUserId(student.getUserId());
            if (attendanceDTO.getDate() != null) {
                attendance.setCheckIn(attendanceDTO.getDate());
            } else {
                attendance.setCheckIn(LocalDateTime.now());
            }
            return attendanceRepository.save(attendance).getId();
        }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public List<AttendanceListDTO> getList(AttendanceFilter filter) {
        List<Attendance> list = attendanceRepository.getListByFilter(filter);
        List<AttendanceListDTO> result = new ArrayList<>();
        Map<ItemDTO, List<Attendance>> map = list.stream()
                .collect(Collectors.groupingBy(
                        attendance -> new ItemDTO(attendance.getUserId(), attendance.getUser().getFullName())
                ));
        YearMonth yearMonth = YearMonth.of(filter.getYear(), filter.getMonth());
        map.forEach((item, attendances) -> {
            List<AttendanceItemDTO> itemDTOS = new ArrayList<>();
            for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
                AttendanceItemDTO attendanceItemDTO = new AttendanceItemDTO();
                attendanceItemDTO.setDate(null);
                attendanceItemDTO.setDay(i);
                attendanceItemDTO.setStatus("ABSENT");
                for (Attendance attendance : attendances) {
                    if (i == attendance.getCheckIn().getDayOfMonth()) {
                        attendanceItemDTO.setDate(attendance.getCheckIn());
                        attendanceItemDTO.setStatus("PRESENT");
                    }
                }
                itemDTOS.add(attendanceItemDTO);
            }
            AttendanceListDTO attendanceListDTO = new AttendanceListDTO();
            attendanceListDTO.setStudent(item);
            attendanceListDTO.setItems(itemDTOS);
            result.add(attendanceListDTO);
        });
        return result;
    }
}
