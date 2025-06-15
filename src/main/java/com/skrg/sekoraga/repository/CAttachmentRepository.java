package com.skrg.sekoraga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.skrg.sekoraga.domain.CAttachment;

@Repository
public interface CAttachmentRepository extends JpaRepository<CAttachment, Long>, JpaSpecificationExecutor<CAttachment> {
}
