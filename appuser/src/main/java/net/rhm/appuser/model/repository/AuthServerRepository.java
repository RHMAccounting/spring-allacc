package net.rhm.appuser.model.repository;

import net.rhm.appuser.model.entity.AuthServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthServerRepository extends JpaRepository<AuthServer, Long> {

    AuthServer findByName(String name);
}
