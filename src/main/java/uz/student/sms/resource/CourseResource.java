package uz.student.sms.resource;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.student.sms.dto.course.CourseDTO;
import uz.student.sms.dto.course.CourseListingDTO;
import uz.student.sms.service.CourseService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/courses")
public class CourseResource {

    private final CourseService courseService;

    @PostMapping
    public Long create(@RequestBody @Valid CourseDTO courseDTO) {
        return courseService.create(courseDTO);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody @Valid CourseDTO courseDTO) {
        return courseService.update(id, courseDTO);
    }

    @GetMapping
    public List<CourseListingDTO> getList() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public CourseListingDTO get(@PathVariable Long id) {
        return courseService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }
}
