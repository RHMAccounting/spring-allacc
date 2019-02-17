package net.rhm.appuser.model.embeddable;

import lombok.Data;
import net.rhm.appuser.model.entity.AuthServer;
import net.rhm.appuser.model.entity.User;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class AuthUser implements Serializable {


    @NotNull
    // Relationship
    @OneToOne()
    @JoinColumn(name = "auth_server_id", referencedColumnName = "id")
    private AuthServer authServer;

    @NotNull
    // Relationship
    @OneToOne()
    @JoinColumn(name = "app_user_id", referencedColumnName = "id")
    private User user;

    public AuthServer getAuthServer() {
        return authServer;
    }

    public void setAuthServer(AuthServer authServer) {
        this.authServer = authServer;
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
        AuthUser authUser = (AuthUser) o;
        return Objects.equals(authServer, authUser.authServer) &&
                Objects.equals(user, authUser.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authServer, user);
    }
}
