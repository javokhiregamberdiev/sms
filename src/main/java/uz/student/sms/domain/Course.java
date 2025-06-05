package uz.student.sms.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.student.sms.domain.base.DataEntity;
import uz.student.sms.dto.ItemDTO;
import uz.student.sms.dto.course.CourseListingDTO;

import java.io.Serializable;

@Entity
@Table(name = "courses")
@Data
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE courses SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = 'false'")
public class Course extends DataEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    public CourseListingDTO toListingDTO() {
        CourseListingDTO courseListingDTO = new CourseListingDTO();
        courseListingDTO.setId(this.getId());
        courseListingDTO.setName(this.getName());
        courseListingDTO.setDescription(this.getDescription());
        courseListingDTO.setCreatedBy(this.getCreatedByAsItemDTO());
        courseListingDTO.setCreatedDate(this.getCreatedDate());
        return courseListingDTO;
    }

    public ItemDTO toItemDTO() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(this.getId());
        itemDTO.setName(this.getName());
        return itemDTO;
    }
}
