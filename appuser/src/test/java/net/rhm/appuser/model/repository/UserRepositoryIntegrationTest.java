package net.rhm.appuser.model.repository;

import net.rhm.appuser.model.embeddable.AuthUser;
import net.rhm.appuser.model.entity.AuthServer;
import net.rhm.appuser.model.entity.AuthServerUser;
import net.rhm.appuser.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthServerUserRepository authUserServerRepository;

    @Test
    public void whenFindByAuthUser_thenReturnUser() {

        // Given
        User user = new User();
        user.setEmail("hello@gmail.com");
        user.setUserName("Bill");
        user.setFirstName("Bill");
        user.setLastName("Huji");

        AuthServer authServer = new AuthServer();
        authServer.setName("Okta");
        authServer.setProtocol("OAuth2");

        AuthUser authUser = new AuthUser();
        authUser.setAuthServer(authServer);
        authUser.setUser(user);

        AuthServerUser authServerUser = new AuthServerUser();
        authServerUser.setAuthUser(authUser);
        authServerUser.setUserId("4524Ldflmsls");

        entityManager.persist(user);
        entityManager.persist(authServer);
        entityManager.persist(authServerUser);

        entityManager.flush();

        // When
        AuthServerUser authSrvUser = authUserServerRepository.findByUserId("4524Ldflmsls");

        // Then
        assertThat(authSrvUser.getAuthUser().getUser().getEmail())
            .isEqualTo(user.getEmail());

    }


}
