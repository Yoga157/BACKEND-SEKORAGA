package com.skrg.sekoraga.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skrg.sekoraga.domain.CAttachment;
import com.skrg.sekoraga.domain.criteria.CAttachmentCriteria;
import com.skrg.sekoraga.domain.dto.CAttachmentDTO;
import com.skrg.sekoraga.repository.CAttachmentRepository;
import com.skrg.sekoraga.service.mapper.CAttachmentMapper;
import com.skrg.sekoraga.util.filter.SpecificationBuilder;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CAttachmentQueryService extends SpecificationBuilder<CAttachment> {
    private final Logger log = LoggerFactory.getLogger(CAttachmentQueryService.class);

    @Autowired
    private CAttachmentRepository cAttachmentRepository;

    @Autowired
    private CAttachmentMapper cAttachmentMapper;

    @Transactional(readOnly = true)
    public List<CAttachmentDTO> findByCriteria(CAttachmentCriteria criteria) {
        log.debug("Find by criteria : {}", criteria);
        final Specification<CAttachment> specification = createSpecification(criteria);
        return cAttachmentRepository.findAll(specification).stream().map(cAttachmentMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Page<CAttachmentDTO> findByCriteria(CAttachmentCriteria criteria, Pageable page) {
        log.debug("Find by criteria : {}, page: {}", criteria, page);
        final Specification<CAttachment> specification = createSpecification(criteria);
        return cAttachmentRepository.findAll(specification, page).map(cAttachmentMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Long countByCriteria(CAttachmentCriteria criteria) {
        log.debug("Count by criteria : {}", criteria);
        final Specification<CAttachment> specification = createSpecification(criteria);
        return cAttachmentRepository.count(specification);
    }

    protected Specification<CAttachment> createSpecification(CAttachmentCriteria criteria) {
        Specification<CAttachment> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), "attachmentId"));
            }
            if (criteria.getFileName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileName(), "fileName"));
            }
            if (criteria.getMimeType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMimeType(), "mimeType"));
            }
            if (criteria.getDocumentType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDocumentType(), "documentType"));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), "type"));
            }
        }
        return specification;
    }
}
