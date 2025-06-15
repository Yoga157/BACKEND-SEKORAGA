package com.skrg.sekoraga.service;

import com.skrg.sekoraga.domain.AdUserAuthority;
import com.skrg.sekoraga.domain.AdUserAuthorityId;
import com.skrg.sekoraga.repository.AdUserAuthorityRepository;
import com.skrg.sekoraga.service.dto.AdUserAuthorityDTO;
import com.skrg.sekoraga.service.mapper.AdUserAuthorityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdUserAuthorityService {
    private final AdUserAuthorityRepository repository;
    private final AdUserAuthorityMapper mapper;

    public AdUserAuthorityService(AdUserAuthorityRepository repository, AdUserAuthorityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AdUserAuthorityDTO save(AdUserAuthorityDTO dto) {
        AdUserAuthority entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public List<AdUserAuthorityDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public Optional<AdUserAuthorityDTO> findOne(Long userId, Long authorityId) {
        return repository.findById(new AdUserAuthorityId(userId, authorityId)).map(mapper::toDto);
    }

    public void delete(Long userId, Long authorityId) {
        repository.deleteById(new AdUserAuthorityId(userId, authorityId));
    }
}
