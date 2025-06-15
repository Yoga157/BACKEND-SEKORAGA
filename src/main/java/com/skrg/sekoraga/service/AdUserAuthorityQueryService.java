package com.skrg.sekoraga.service;

import com.skrg.sekoraga.repository.AdUserAuthorityRepository;
import com.skrg.sekoraga.service.dto.AdUserAuthorityDTO;
import com.skrg.sekoraga.service.mapper.AdUserAuthorityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdUserAuthorityQueryService {
    private final AdUserAuthorityRepository repository;
    private final AdUserAuthorityMapper mapper;

    public AdUserAuthorityQueryService(AdUserAuthorityRepository repository, AdUserAuthorityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<AdUserAuthorityDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }
}
