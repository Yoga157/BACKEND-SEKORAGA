package com.skrg.sekoraga.service;

import com.skrg.sekoraga.domain.CActivitySchedule;
import com.skrg.sekoraga.repository.CActivityScheduleRepository;
import com.skrg.sekoraga.service.dto.CActivityScheduleDTO;
import com.skrg.sekoraga.service.mapper.CActivityScheduleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CActivityScheduleService {
    private final CActivityScheduleRepository repository;
    private final CActivityScheduleMapper mapper;

    public CActivityScheduleService(CActivityScheduleRepository repository, CActivityScheduleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CActivityScheduleDTO save(CActivityScheduleDTO dto) {
        CActivitySchedule entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public List<CActivityScheduleDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public Optional<CActivityScheduleDTO> findById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<CActivityScheduleDTO> findByUserId(Long userId) {
        return repository.findByUser_UserId(userId).stream().map(mapper::toDto).toList();
    }
}
