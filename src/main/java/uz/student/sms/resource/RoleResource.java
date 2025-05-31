package uz.student.sms.resource;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.student.sms.dto.ItemDTO;
import uz.student.sms.service.RoleService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/roles")
public class RoleResource {

    private final RoleService  roleService;

    @GetMapping
    public List<ItemDTO> getList(){
        return roleService.getList();
    }
}
