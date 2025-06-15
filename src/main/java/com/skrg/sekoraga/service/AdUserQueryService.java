package com.skrg.sekoraga.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skrg.sekoraga.domain.AdUser;
import com.skrg.sekoraga.domain.criteria.AdUserCriteria;
import com.skrg.sekoraga.domain.dto.AdUserDTO;
import com.skrg.sekoraga.repository.AdUserRepository;
import com.skrg.sekoraga.service.mapper.AdUserMapper;
import com.skrg.sekoraga.util.filter.SpecificationBuilder;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AdUserQueryService extends SpecificationBuilder<AdUser> {
    private final Logger log = LoggerFactory.getLogger(AdUserQueryService.class);

    @Autowired
    private AdUserRepository adUserRepository;

    @Autowired
    private AdUserMapper adUserMapper;

    @Transactional(readOnly = true)
    public List<AdUserDTO> findByCriteria(AdUserCriteria criteria) {
        log.debug("Find by criteria : {}", criteria);
        final Specification<AdUser> specification = createSpecification(criteria);
        return adUserRepository.findAll(specification).stream().map(adUserMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Page<AdUserDTO> findByCriteria(AdUserCriteria criteria, Pageable page) {
        log.debug("Find by criteria : {}, page: {}", criteria, page);
        final Specification<AdUser> specification = createSpecification(criteria);
        return adUserRepository.findAll(specification, page).map(adUserMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Long countByCriteria(AdUserCriteria criteria) {
        log.debug("Count by criteria : {}", criteria);
        final Specification<AdUser> specification = createSpecification(criteria);
        return adUserRepository.count(specification);
    }

    protected Specification<AdUser> createSpecification(AdUserCriteria criteria) {
        Specification<AdUser> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), "userId"));
            }
            if (criteria.getUsername() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUsername(), "username"));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), "email"));
            }
        }
        return specification;
    }
}
