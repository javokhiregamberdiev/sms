package uz.student.sms.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.student.sms.domain.Group;
import uz.student.sms.dto.group.GroupDTO;
import uz.student.sms.dto.group.GroupDetailDTO;
import uz.student.sms.exceptions.NotFoundException;
import uz.student.sms.repository.GroupRepository;
import uz.student.sms.service.GroupService;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public Long create(GroupDTO groupDTO) {
        Group group = new Group();
        group.setName(groupDTO.getName());
        group.setTeacherId(groupDTO.getTeacherId());
        group.setCourseId(groupDTO.getCourseId());
        return groupRepository.save(group).getId();
    }

    @Override
    public Long update(Long id, GroupDTO groupDTO) {
        return groupRepository.findById(id).map(group -> {
            group.setName(groupDTO.getName());
            group.setTeacherId(groupDTO.getTeacherId());
            group.setCourseId(groupDTO.getCourseId());
            return groupRepository.save(group).getId();
        }).orElseThrow(() -> new NotFoundException("Group not found"));
    }

    @Override
    public void delete(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public GroupDetailDTO get(Long id) {
        return groupRepository.findById(id).map(Group::toDTO)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    @Override
    public List<GroupDetailDTO> getList() {
        return groupRepository.findAll().stream().map(Group::toDTO).toList();
    }
}
