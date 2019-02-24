package net.rhm.appuser.model.embeddable;

import lombok.Data;
import net.rhm.appuser.model.entity.Company;
import net.rhm.appuser.model.entity.User;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class CompanyUser implements Serializable {
    
    @NotNull
    // Relationship
    @OneToOne()
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @NotNull
    // Relationship
    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyUser that = (CompanyUser) o;
        return Objects.equals(company, that.company) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, user);
    }
}
