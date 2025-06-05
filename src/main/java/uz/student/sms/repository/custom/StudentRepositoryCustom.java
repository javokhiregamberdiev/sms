package uz.student.sms.repository.custom;

import uz.student.sms.domain.Student;
import uz.student.sms.dto.filter.BaseFilter;

import java.util.List;

public interface StudentRepositoryCustom {

    List<Student> findStudentsByFilter(BaseFilter filter);
}
