package uz.student.sms.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.student.sms.domain.Role;
import uz.student.sms.domain.Student;
import uz.student.sms.dto.filter.BaseFilter;
import uz.student.sms.dto.student.StudentDTO;
import uz.student.sms.dto.student.StudentDetailDTO;
import uz.student.sms.dto.student.StudentListDTO;
import uz.student.sms.exceptions.BadRequestException;
import uz.student.sms.exceptions.NotFoundException;
import uz.student.sms.repository.*;
import uz.student.sms.service.StudentService;
import uz.student.sms.service.UserService;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    private void validation(StudentDTO studentDTO) {

        studentRepository.findByCardId(studentDTO.getCardId()).ifPresent(student -> {
            if (!Objects.equals(studentDTO.getId(), student.getUserId())) {
                throw new BadRequestException("CardID already exists");
            }
        });
        userRepository.findByUsername(studentDTO.getUsername()).ifPresent(user -> {
            if (!Objects.equals(studentDTO.getId(), user.getId())) {
                throw new BadRequestException("Username already exists");
            }
        });

        userRepository.findByEmail(studentDTO.getEmail()).ifPresent(user -> {
            if (!Objects.equals(studentDTO.getId(), user.getId())) {
                throw new BadRequestException("Email already exists");
            }
        });

        userRepository.findByPhone(studentDTO.getPhone()).ifPresent(user -> {
            if (!Objects.equals(studentDTO.getId(), user.getId())) {
                throw new BadRequestException("Phone already exists");
            }
        });
    }

    @Override
    public Long create(StudentDTO studentDTO) {
        this.validation(studentDTO);
        Student student = new Student();
        student.setGender(studentDTO.getGender());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setCardId(studentDTO.getCardId());
        if (studentDTO.getRoleIds() == null || studentDTO.getRoleIds().isEmpty()) {
            studentDTO.setRoleIds(roleRepository.findByName("STUDENT").map(Role::getId).stream().toList());
        }
        if (studentDTO.getCourseIds() != null && !studentDTO.getCourseIds().isEmpty()) {
            student.setCourses(courseRepository.findByIdIn(studentDTO.getCourseIds()));
        }
        if (studentDTO.getGroupIds() != null && !studentDTO.getGroupIds().isEmpty()) {
            student.setGroups(groupRepository.findByIdIn(studentDTO.getGroupIds()));
        }
        Long userId = userService.create(studentDTO);
        student.setUserId(userId);
        studentRepository.save(student);
        return student.getId();
    }

    @Override
    public Long update(Long id, StudentDTO studentDTO) {
        return studentRepository.findById(id).map(student -> {
            studentDTO.setId(student.getUserId());
            this.validation(studentDTO);
            student.setGender(studentDTO.getGender());
            student.setDateOfBirth(studentDTO.getDateOfBirth());
            student.setCardId(studentDTO.getCardId());
            if (studentDTO.getRoleIds() == null || studentDTO.getRoleIds().isEmpty()) {
                studentDTO.setRoleIds(roleRepository.findByName("STUDENT").map(Role::getId).stream().toList());
            }
            userService.update(student.getUserId(), studentDTO);
            return studentRepository.save(student).getId();
        }).orElseThrow(() -> new NotFoundException("Student not fount"));
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDetailDTO get(Long id) {
        return studentRepository.findById(id).map(student -> {
            StudentDetailDTO studentDetailDTO = new StudentDetailDTO();
            studentDetailDTO.setId(student.getId());
            studentDetailDTO.setFirstName(student.getUser().getFirstName());
            studentDetailDTO.setLastName(student.getUser().getLastName());
            studentDetailDTO.setEmail(student.getUser().getEmail());
            studentDetailDTO.setPhone(student.getUser().getPhone());
            studentDetailDTO.setGender(student.getGender());
            studentDetailDTO.setDateOfBirth(student.getDateOfBirth());
            studentDetailDTO.setUsername(student.getUser().getUsername());
            studentDetailDTO.setCreatedBy(student.getCreatedByAsItemDTO());
            studentDetailDTO.setCardId(student.getCardId());
            return studentDetailDTO;
        }).orElseThrow(() -> new NotFoundException("Student not found"));
    }

    @Override
    public List<StudentListDTO> getList(BaseFilter filter) {
        List<Student> students = studentRepository.findStudentsByFilter(filter);
        return students.stream().map(student -> {
            StudentListDTO studentListDTO = new StudentListDTO();
            studentListDTO.setId(student.getId());
            studentListDTO.setCreatedDate(student.getCreatedDate());
            studentListDTO.setFirstName(student.getUser().getFirstName());
            studentListDTO.setLastName(student.getUser().getLastName());
            studentListDTO.setEmail(student.getUser().getEmail());
            studentListDTO.setPhone(student.getUser().getPhone());
            return studentListDTO;
        }).toList();
    }
}
