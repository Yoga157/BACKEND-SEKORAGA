package com.skrg.sekoraga.repository;

import com.skrg.sekoraga.domain.AdUserAuthority;
import com.skrg.sekoraga.domain.AdUserAuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUserAuthorityRepository extends JpaRepository<AdUserAuthority, AdUserAuthorityId> {
}
