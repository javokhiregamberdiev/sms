package uz.student.sms.service;

import uz.student.sms.dto.group.GroupDTO;
import uz.student.sms.dto.group.GroupDetailDTO;

import java.util.List;

public interface GroupService {

    Long create(GroupDTO groupDTO);

    Long update(Long id, GroupDTO groupDTO);

    void delete(Long id);

    GroupDetailDTO get(Long id);

    List<GroupDetailDTO> getList();
}
