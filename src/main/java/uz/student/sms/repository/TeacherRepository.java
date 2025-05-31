package uz.student.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.student.sms.domain.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
