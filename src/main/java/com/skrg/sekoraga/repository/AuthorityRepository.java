package com.skrg.sekoraga.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skrg.sekoraga.domain.AdAuthority;

public interface AuthorityRepository extends JpaRepository<AdAuthority, Long>, JpaSpecificationExecutor<AdAuthority> {
    Optional<AdAuthority> findByName(String name);
}
