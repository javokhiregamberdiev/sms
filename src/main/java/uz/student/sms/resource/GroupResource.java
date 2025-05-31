package uz.student.sms.resource;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.student.sms.dto.group.GroupDTO;
import uz.student.sms.dto.group.GroupDetailDTO;
import uz.student.sms.service.GroupService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/groups")
public class GroupResource {

    private final GroupService groupService;

    @PostMapping
    public Long create(@RequestBody GroupDTO groupDTO) {
        return groupService.create(groupDTO);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody GroupDTO groupDTO) {
        return groupService.update(id, groupDTO);
    }

    @GetMapping
    public List<GroupDetailDTO> getList() {
        return groupService.getList();
    }

    @GetMapping("/{id}")
    public GroupDetailDTO get(@PathVariable Long id) {
        return groupService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        groupService.delete(id);
    }
}
