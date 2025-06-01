package uz.student.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.student.sms.domain.Attendance;
import uz.student.sms.repository.custom.AttendanceRepositoryCustom;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>, AttendanceRepositoryCustom {

    @Query(value = "SELECT a FROM Attendance a " +
            "WHERE a.checkIn BETWEEN :startDate AND :endDate " +
            "AND a.userId =:userId " +
            "ORDER BY a.checkIn ASC")
    Optional<Attendance> findFirstAttendanceOfDay(@Param("startDate") LocalDateTime startDate,
                                                  @Param("endDate") LocalDateTime endDate,
                                                  @Param("userId") Long userId);
}
