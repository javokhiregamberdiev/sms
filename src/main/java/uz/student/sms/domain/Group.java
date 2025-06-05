package uz.student.sms.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.student.sms.domain.base.DataEntity;
import uz.student.sms.dto.group.GroupDetailDTO;

import java.io.Serializable;

@Entity
@Table(name = "groups")
@Data
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE groups SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = 'false'")
public class Group extends DataEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "teacher_id")
    private Long teacherId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private Teacher teacher;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;


    public GroupDetailDTO toDTO() {
        GroupDetailDTO groupDetailDTO = new GroupDetailDTO();
        groupDetailDTO.setId(this.getId());
        groupDetailDTO.setName(this.getName());
        groupDetailDTO.setTeacher(this.getTeacher().toItemDTO());
        groupDetailDTO.setCreatedDate(this.getCreatedDate());
        groupDetailDTO.setCreatedBy(this.getCreatedByAsItemDTO());
        groupDetailDTO.setCourse(this.course.toItemDTO());
        return groupDetailDTO;
    }
}
