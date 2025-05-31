package uz.student.sms.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.student.sms.domain.Course;
import uz.student.sms.dto.course.CourseDTO;
import uz.student.sms.dto.course.CourseListingDTO;
import uz.student.sms.repository.CourseRepository;
import uz.student.sms.service.CourseService;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Long create(CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        return courseRepository.save(course).getId();
    }

    @Override
    public Long update(Long id, CourseDTO courseDTO) {
        return courseRepository.findById(id).map(course -> {
                    course.setName(courseDTO.getName());
                    course.setDescription(courseDTO.getDescription());
                    return courseRepository.save(course).getId();
                })
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseListingDTO get(Long id) {
        return courseRepository.findById(id).map(Course::toListingDTO)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public List<CourseListingDTO> getAll() {
        return courseRepository.findAll().stream().map(Course::toListingDTO).toList();
    }
}
