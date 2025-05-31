package uz.student.sms.resource;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.student.sms.dto.teacher.TeacherDTO;
import uz.student.sms.dto.teacher.TeacherDetailDTO;
import uz.student.sms.dto.teacher.TeacherListingDTO;
import uz.student.sms.service.TeacherService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/teachers")
public class TeacherResource {

    private final TeacherService teacherService;

    @PostMapping
    public Long create(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.create(teacherDTO);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
        return teacherService.update(id, teacherDTO);
    }

    @GetMapping
    public List<TeacherListingDTO> getList() {
        return teacherService.getList();
    }

    @GetMapping("/{id}")
    public TeacherDetailDTO get(@PathVariable Long id) {
        return teacherService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);
    }
}
