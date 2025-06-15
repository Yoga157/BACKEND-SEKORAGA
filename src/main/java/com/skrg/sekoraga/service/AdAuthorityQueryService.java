package com.skrg.sekoraga.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skrg.sekoraga.domain.AdAuthority;
import com.skrg.sekoraga.domain.criteria.AdAuthorityCriteria;
import com.skrg.sekoraga.domain.dto.AdAuthorityDTO;
import com.skrg.sekoraga.repository.AuthorityRepository;
import com.skrg.sekoraga.service.mapper.AdAuthorityMapper;
import com.skrg.sekoraga.util.filter.SpecificationBuilder;

@Service
@Transactional(readOnly = true)
public class AdAuthorityQueryService extends SpecificationBuilder<AdAuthority> {

    private final Logger log = LoggerFactory.getLogger(AdAuthorityQueryService.class);

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AdAuthorityMapper authorityMapper;

    @Transactional(readOnly = true)
    public List<AdAuthorityDTO> findByCriteria(AdAuthorityCriteria criteria) {
        log.debug("Find by criteria : {}", criteria);
        final Specification<AdAuthority> specification = createSpecification(criteria);
        return authorityRepository.findAll(specification).stream().map(authorityMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Page<AdAuthorityDTO> findByCriteria(AdAuthorityCriteria criteria, Pageable page) {
        log.debug("Find by criteria : {}, page: {}", criteria, page);
        final Specification<AdAuthority> specification = createSpecification(criteria);
        return authorityRepository.findAll(specification, page).map(authorityMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Long countByCriteria(AdAuthorityCriteria criteria) {
        log.debug("Count by criteria : {}", criteria);
        final Specification<AdAuthority> specification = createSpecification(criteria);
        return authorityRepository.count(specification);
    }

    protected Specification<AdAuthority> createSpecification(AdAuthorityCriteria criteria) {
        Specification<AdAuthority> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), "id"));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), "name"));
            }
        }
        return specification;
    }
}
