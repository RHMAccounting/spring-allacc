package net.rhm.appuser.model.repository;

import net.rhm.appuser.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}