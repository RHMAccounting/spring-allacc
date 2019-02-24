package net.rhm.appuser.model.entity;

import lombok.Data;
import net.rhm.appuser.model.embeddable.CompanyUser;

import javax.persistence.*;

/**
 * Assigned user roles for specific company
 */
@Entity
@Data
@Table(name="user_role_company")
public class UserRoleCompany {

    @EmbeddedId
    private CompanyUser companyUser;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}
