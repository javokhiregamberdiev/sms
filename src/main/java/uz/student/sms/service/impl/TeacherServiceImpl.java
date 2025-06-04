package uz.student.sms.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.student.sms.domain.Role;
import uz.student.sms.domain.Teacher;
import uz.student.sms.dto.teacher.TeacherDTO;
import uz.student.sms.dto.teacher.TeacherDetailDTO;
import uz.student.sms.dto.teacher.TeacherListingDTO;
import uz.student.sms.exceptions.NotFoundException;
import uz.student.sms.repository.RoleRepository;
import uz.student.sms.repository.TeacherRepository;
import uz.student.sms.service.TeacherService;
import uz.student.sms.service.UserService;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Override
    public Long create(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setSpeciality(teacherDTO.getSpeciality());
        teacher.setGender(teacherDTO.getGender());
        teacher.setDateOfBirth(teacherDTO.getDateOfBirth());
        if (teacherDTO.getRoleIds() == null || teacherDTO.getRoleIds().isEmpty()) {
            teacherDTO.setRoleIds(roleRepository.findByName("TEACHER").map(Role::getId).stream().toList());
        }
        Long userId = userService.create(teacherDTO);
        teacher.setUserId(userId);
        return teacherRepository.save(teacher).getId();
    }

    @Override
    public Long update(Long id, TeacherDTO teacherDTO) {
        return teacherRepository.findById(id).map(teacher -> {
            teacher.setSpeciality(teacherDTO.getSpeciality());
            teacher.setGender(teacherDTO.getGender());
            teacher.setDateOfBirth(teacherDTO.getDateOfBirth());
            if (teacherDTO.getRoleIds() == null || teacherDTO.getRoleIds().isEmpty()) {
                teacherDTO.setRoleIds(roleRepository.findByName("TEACHER").map(Role::getId).stream().toList());
            }
            userService.update(teacher.getUserId(), teacherDTO);
            return teacherRepository.save(teacher).getId();
        }).orElseThrow(() -> new NotFoundException("Teacher not found"));
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public TeacherDetailDTO get(Long id) {
        return teacherRepository.findById(id).map(teacher -> {
            TeacherDetailDTO teacherDetailDTO = new TeacherDetailDTO();
            teacherDetailDTO.setId(teacher.getId());
            teacherDetailDTO.setFirstName(teacher.getUser().getFirstName());
            teacherDetailDTO.setLastName(teacher.getUser().getLastName());
            teacherDetailDTO.setSpeciality(teacher.getSpeciality());
            teacherDetailDTO.setEmail(teacher.getUser().getEmail());
            teacherDetailDTO.setPhone(teacher.getUser().getPhone());
            teacherDetailDTO.setGender(teacher.getGender());
            teacherDetailDTO.setDateOfBirth(teacher.getDateOfBirth());
            teacherDetailDTO.setUsername(teacher.getUser().getUsername());
            teacherDetailDTO.setCreatedBy(teacher.getCreatedByAsItemDTO());
            return teacherDetailDTO;
        }).orElseThrow(() -> new NotFoundException("Teacher not found"));
    }

    @Override
    public List<TeacherListingDTO> getList() {
        return teacherRepository.findAll().stream().map(teacher -> {
            TeacherListingDTO teacherListingDTO = new TeacherListingDTO();
            teacherListingDTO.setId(teacher.getId());
            teacherListingDTO.setFirstName(teacher.getUser().getFirstName());
            teacherListingDTO.setLastName(teacher.getUser().getLastName());
            teacherListingDTO.setSpeciality(teacher.getSpeciality());
            teacherListingDTO.setEmail(teacher.getUser().getEmail());
            teacherListingDTO.setPhone(teacher.getUser().getPhone());
            teacherListingDTO.setCreatedDate(teacher.getCreatedDate());
            return teacherListingDTO;
        }).toList();
    }
}
