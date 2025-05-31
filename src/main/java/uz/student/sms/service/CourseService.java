package uz.student.sms.service;

import uz.student.sms.dto.course.CourseDTO;
import uz.student.sms.dto.course.CourseListingDTO;

import java.util.List;

public interface CourseService {

    Long create(CourseDTO courseDTO);

    Long update(Long id, CourseDTO courseDTO);

    void delete(Long id);

    CourseListingDTO get(Long id);

    List<CourseListingDTO> getAll();
}
