package uz.student.sms.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import uz.student.sms.domain.base.DataEntity;
import uz.student.sms.dto.ItemDTO;
import uz.student.sms.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "teachers")
@Data
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE teachers SET deleted = true WHERE id = ?")
public class Teacher extends DataEntity implements Serializable {

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public ItemDTO toItemDTO() {
        return new ItemDTO(getId(), getUser().getUsername());
    }
}
