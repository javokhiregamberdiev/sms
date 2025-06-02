package uz.student.sms.resource;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.student.sms.dto.student.StudentDTO;
import uz.student.sms.dto.student.StudentDetailDTO;
import uz.student.sms.dto.student.StudentListDTO;
import uz.student.sms.service.StudentService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/students")
public class StudentResource {

    private final StudentService studentService;

    @PostMapping
    public Long create(@RequestBody @Valid StudentDTO studentDTO) {
        return studentService.create(studentDTO);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody @Valid StudentDTO studentDTO) {
        return studentService.update(id, studentDTO);
    }

    @GetMapping
    public List<StudentListDTO> getList() {
        return studentService.getList();
    }

    @GetMapping("/{id}")
    public StudentDetailDTO get(@PathVariable Long id) {
        return studentService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}
