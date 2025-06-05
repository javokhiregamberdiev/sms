package uz.student.sms.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.student.sms.domain.base.DataEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
@Data
@SQLDelete(sql = "UPDATE roles SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = 'false'")
public class Role extends DataEntity {

    @Column(name = "role_name", unique = true, nullable = false)
    private String name;

//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    private List<User> users;
}
