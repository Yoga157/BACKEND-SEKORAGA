package com.skrg.sekoraga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.skrg.sekoraga.domain.AdUser;

import java.util.Optional;

@Repository
public interface AdUserRepository extends JpaRepository<AdUser, Long>, JpaSpecificationExecutor<AdUser> {

    String USERS_BY_USERNAME_CACHE = "usersByUsername";

    String USERS_BY_EMAIL_CACHE = "usersByEmail";

    Optional<AdUser> findFirstByUsername(String username);

    Optional<AdUser> findFirstByEmail(String email);

    Optional<AdUser> findOneWithAuthoritiesByUsernameIgnoreCase(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<AdUser> findFirstByUsernameOrEmail(String username, String email);
}
