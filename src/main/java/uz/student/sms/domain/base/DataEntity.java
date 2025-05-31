package uz.student.sms.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class DataEntity extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime modifiedDate;

    private boolean deleted;

    @PreUpdate
    public void preUpdate() {
        setModifiedDate(LocalDateTime.now());
    }

    @PrePersist
    public void prePersist() {
        if (getCreatedDate() == null) {
            this.setCreatedDate(LocalDateTime.now());
        }
        this.setModifiedDate(LocalDateTime.now());
    }
}
