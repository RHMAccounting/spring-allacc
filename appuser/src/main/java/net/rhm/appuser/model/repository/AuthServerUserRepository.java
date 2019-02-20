package net.rhm.appuser.model.repository;

import net.rhm.appuser.model.embeddable.AuthUser;
import net.rhm.appuser.model.entity.AuthServerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface AuthServerUserRepository extends JpaRepository<AuthServerUser, AuthUser> {

    AuthServerUser findByUserId(String userId);
}
