package com.skrg.sekoraga.service;

import com.skrg.sekoraga.domain.CExerciseVideo;
import com.skrg.sekoraga.repository.CExerciseVideoRepository;
import com.skrg.sekoraga.service.dto.CExerciseVideoDTO;
import com.skrg.sekoraga.service.mapper.CExerciseVideoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CExerciseVideoService {
    private final CExerciseVideoRepository repository;
    private final CExerciseVideoMapper mapper;

    public CExerciseVideoService(CExerciseVideoRepository repository, CExerciseVideoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CExerciseVideoDTO save(CExerciseVideoDTO dto) {
        CExerciseVideo entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public List<CExerciseVideoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public Optional<CExerciseVideoDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
