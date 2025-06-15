package com.skrg.sekoraga.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skrg.sekoraga.domain.AdAuthority;
import com.skrg.sekoraga.domain.dto.AdAuthorityDTO;
import com.skrg.sekoraga.repository.AuthorityRepository;
import com.skrg.sekoraga.service.mapper.AdAuthorityMapper;

@Service
@Transactional
public class AdAuthorityService {

    private final Logger log = LoggerFactory.getLogger(AdAuthorityService.class);

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AdAuthorityMapper authorityMapper;

    public AdAuthorityDTO save(AdAuthorityDTO authorityDTO) {
        log.debug("Request to save Authority : {}", authorityDTO);
        AdAuthority authority = authorityMapper.toEntity(authorityDTO);
        authority = authorityRepository.save(authority);
        return authorityMapper.toDto(authority);
    }

    @Transactional(readOnly = true)
    public Page<AdAuthorityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Authorities");
        return authorityRepository.findAll(pageable).map(authorityMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<AdAuthorityDTO> findOne(String id) {
        log.debug("Request to get Authority : {}", id);
        Long longId = parseId(id);
        return authorityRepository.findById(longId).map(authorityMapper::toDto);
    }

    public void delete(String id) {
        log.debug("Request to delete Authority : {}", id);
        Long longId = parseId(id);
        authorityRepository.deleteById(longId);
    }

    private Long parseId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID format: " + id, e);
        }
    }
}
