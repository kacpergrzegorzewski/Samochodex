package edu.bada.samochodex.security.auth;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplicationUserDao extends CrudRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByUsername(String username);
}
