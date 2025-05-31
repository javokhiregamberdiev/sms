package uz.student.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.student.sms.domain.Group;

import java.util.List;
import java.util.Set;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Set<Group> findByIdIn(List<Long> ids);
}
