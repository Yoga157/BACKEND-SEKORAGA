package com.skrg.sekoraga.service.query;

import com.skrg.sekoraga.repository.AdUserActivityLogRepository;
import com.skrg.sekoraga.service.dto.AdUserActivityLogDTO;
import com.skrg.sekoraga.service.mapper.AdUserActivityLogMapper;
import com.skrg.sekoraga.service.criteria.AdUserActivityLogCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdUserActivityLogQueryService {
    private final AdUserActivityLogRepository repository;
    private final AdUserActivityLogMapper mapper;

    public AdUserActivityLogQueryService(AdUserActivityLogRepository repository, AdUserActivityLogMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<AdUserActivityLogDTO> findByCriteria(AdUserActivityLogCriteria criteria, Pageable pageable) {
        // Implement filtering by criteria if needed
        return repository.findAll(pageable).map(mapper::toDto);
    }

    public long countByCriteria(AdUserActivityLogCriteria criteria) {
        // Implement filtering by criteria if needed
        return repository.count();
    }
}
