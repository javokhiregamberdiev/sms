package uz.student.sms.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.student.sms.domain.base.DataEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendances")
@Data
@EqualsAndHashCode(callSuper = true)
@SQLRestriction(value = "deleted = 'false'")
@SQLDelete(sql = "UPDATE attendances SET deleted = true WHERE id = ?")
public class Attendance extends DataEntity implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "check_in")
    private LocalDateTime checkIn;
}
