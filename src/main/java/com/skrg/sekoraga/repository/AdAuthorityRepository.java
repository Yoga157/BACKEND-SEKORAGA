package com.skrg.sekoraga.repository;

import com.skrg.sekoraga.domain.AdAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdAuthorityRepository extends JpaRepository<AdAuthority, Long> {
    Optional<AdAuthority> findFirstByName(String name);
}
