package uz.student.sms.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.student.sms.dto.ItemDTO;
import uz.student.sms.repository.RoleRepository;
import uz.student.sms.service.RoleService;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<ItemDTO> getList() {
        return roleRepository.findAll()
                .stream()
                .map(role -> new ItemDTO(role.getId(), role.getName())).toList();
    }
}
