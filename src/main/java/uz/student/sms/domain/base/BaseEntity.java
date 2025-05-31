package uz.student.sms.domain.base;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.student.sms.domain.User;
import uz.student.sms.dto.ItemDTO;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @Column(name = "created_by_id")
    private Long createdById;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", insertable = false, updatable = false)
    private User createdBy;

    public ItemDTO getCreatedByAsItemDTO() {
        return this.getCreatedBy() != null ? new ItemDTO(getCreatedBy().getId(), getCreatedBy().getUsername()) : null;
    }
}
