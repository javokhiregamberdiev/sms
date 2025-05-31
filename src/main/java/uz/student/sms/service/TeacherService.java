package uz.student.sms.service;

import uz.student.sms.dto.teacher.TeacherDTO;
import uz.student.sms.dto.teacher.TeacherDetailDTO;
import uz.student.sms.dto.teacher.TeacherListingDTO;

import java.util.List;

public interface TeacherService {

    Long create(TeacherDTO teacherDTO);

    Long update(Long id, TeacherDTO teacherDTO);

    void  delete(Long id);

    TeacherDetailDTO get(Long id);

    List<TeacherListingDTO> getList();
}
