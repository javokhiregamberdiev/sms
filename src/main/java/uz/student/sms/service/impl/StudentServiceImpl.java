package uz.student.sms.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.student.sms.domain.Role;
import uz.student.sms.domain.Student;
import uz.student.sms.dto.student.StudentDTO;
import uz.student.sms.dto.student.StudentDetailDTO;
import uz.student.sms.dto.student.StudentListDTO;
import uz.student.sms.exceptions.BadRequestException;
import uz.student.sms.exceptions.NotFoundException;
import uz.student.sms.repository.*;
import uz.student.sms.service.StudentService;
import uz.student.sms.service.UserService;

import java.util.List;

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
        if (studentDTO.getCardId() == null) {
            throw new BadRequestException("CardID is required");
        }
        if (studentDTO.getUsername() == null) {
            throw new BadRequestException("Username is required");
        }
        if (studentDTO.getPassword() == null) {
            throw new BadRequestException("Password is required");
        }
        if (studentDTO.getFirstName() == null) {
            throw new BadRequestException("First name is required");
        }
        if (studentDTO.getLastName() == null) {
            throw new BadRequestException("Last name is required");
        }
        if (studentDTO.getPhone() == null) {
            throw new BadRequestException("Phone is required");
        }
        if (studentDTO.getEmail() == null) {
            throw new BadRequestException("Email is required");
        }
        if (studentRepository.findByCardId(studentDTO.getCardId()).isPresent()) {
            throw new BadRequestException("CardID already exists");
        }
        if (userRepository.findByUsername(studentDTO.getUsername()).isPresent()) {
            throw new BadRequestException("Username already exists");
        }
        if (userRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }
        if (userRepository.findByPhone(studentDTO.getPhone()).isPresent()) {
            throw new BadRequestException("Phone already exists");
        }
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
            studentDetailDTO.setFirstName(student.getUser().getPhone());
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
    public List<StudentListDTO> getList() {
        List<Student> students = studentRepository.findAll();
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
