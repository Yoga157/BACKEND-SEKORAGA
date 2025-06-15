package com.skrg.sekoraga.service.query;

import com.skrg.sekoraga.repository.CActivityScheduleRepository;
import com.skrg.sekoraga.service.dto.CActivityScheduleDTO;
import com.skrg.sekoraga.service.mapper.CActivityScheduleMapper;
import com.skrg.sekoraga.service.criteria.CActivityScheduleCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CActivityScheduleQueryService {
    private final CActivityScheduleRepository repository;
    private final CActivityScheduleMapper mapper;

    public CActivityScheduleQueryService(CActivityScheduleRepository repository, CActivityScheduleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<CActivityScheduleDTO> findByCriteria(CActivityScheduleCriteria criteria, Pageable pageable) {
        // Implement filtering by criteria if needed
        return repository.findAll(pageable).map(mapper::toDto);
    }

    public long countByCriteria(CActivityScheduleCriteria criteria) {
        // Implement filtering by criteria if needed
        return repository.count();
    }
}
