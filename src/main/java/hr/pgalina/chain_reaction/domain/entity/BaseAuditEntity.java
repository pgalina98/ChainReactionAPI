package hr.pgalina.chain_reaction.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseAuditEntity implements Serializable {

    @Column(name = "created_by")
    protected String createdBy;

    @Column(name = "created_timestamp")
    protected LocalDateTime createdTimestamp;

    @Column(name = "modified_by")
    protected String modifiedBy;

    @Column(name = "modified_timestamp")
    protected LocalDateTime modifiedTimestamp;

    @PrePersist
    public void onPrePersist() {
        final String currentUser = getCurrentUser().orElse(null);

        createdBy = currentUser;
        modifiedBy = currentUser;
        createdTimestamp = LocalDateTime.now();
        modifiedTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        final String modifiedBy = getCurrentUser().orElse(null);

        if (Objects.nonNull(modifiedBy)) {
            this.modifiedBy = modifiedBy;
        }

        modifiedTimestamp = LocalDateTime.now();
    }

    private Optional<String> getCurrentUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(value -> value.getName());
    }
}
