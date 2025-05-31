package uz.student.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.student.sms.domain.Attendance;
import uz.student.sms.repository.custom.AttendanceRepositoryCustom;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>, AttendanceRepositoryCustom {
}
