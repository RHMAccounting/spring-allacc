package net.rhm.appuser.repository;

import net.rhm.appuser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}