package net.rhm.appuser.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.rhm.appuser.model.AuditModel;
import net.rhm.appuser.model.embeddable.AuthUser;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="app_authserveruser")
public class AuthServerUser extends AuditModel {

    @EmbeddedId
    private AuthUser authUser;

    @NotBlank
    @Column(unique = true)
    private String userId;
}
