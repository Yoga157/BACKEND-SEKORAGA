package com.skrg.sekoraga.service;

import com.skrg.sekoraga.domain.AdUserActivityLog;
import com.skrg.sekoraga.repository.AdUserActivityLogRepository;
import com.skrg.sekoraga.service.dto.AdUserActivityLogDTO;
import com.skrg.sekoraga.service.mapper.AdUserActivityLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdUserActivityLogService {
    private final AdUserActivityLogRepository repository;
    private final AdUserActivityLogMapper mapper;

    public AdUserActivityLogService(AdUserActivityLogRepository repository, AdUserActivityLogMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AdUserActivityLogDTO save(AdUserActivityLogDTO dto) {
        AdUserActivityLog entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public List<AdUserActivityLogDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public Optional<AdUserActivityLogDTO> findById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<AdUserActivityLogDTO> findByUserId(Long userId) {
        return repository.findByUser_UserId(userId).stream().map(mapper::toDto).toList();
    }
}
