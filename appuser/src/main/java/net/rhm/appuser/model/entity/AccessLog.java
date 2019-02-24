package net.rhm.appuser.model.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Tracking user accesses via logs
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="app_access_log")
public class AccessLog {

    @Id
    @GeneratedValue(generator = "log_generator")
    @SequenceGenerator(
            name = "log_generator",
            sequenceName = "log_sequence",
            initialValue = 1
    )
    private Long id;

    @NotNull
    // Relationship
    @OneToOne()
    @JoinColumn(name = "app_user_id", referencedColumnName = "id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "access_time", nullable = false, updatable = false)
    @CreatedDate
    private Date accessTime;

}
