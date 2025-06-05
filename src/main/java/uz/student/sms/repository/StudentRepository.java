package uz.student.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.student.sms.domain.Student;
import uz.student.sms.repository.custom.StudentRepositoryCustom;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom {

    Optional<Student> findByCardId(String cardId);
}
