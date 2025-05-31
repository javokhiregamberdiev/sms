package uz.student.sms.service;

import uz.student.sms.dto.student.StudentDTO;
import uz.student.sms.dto.student.StudentDetailDTO;
import uz.student.sms.dto.student.StudentListDTO;

import java.util.List;

public interface StudentService {

    Long create(StudentDTO studentDTO);

    Long update(Long id, StudentDTO studentDTO);

    void delete(Long id);

    StudentDetailDTO get(Long id);

    List<StudentListDTO> getList();
}
