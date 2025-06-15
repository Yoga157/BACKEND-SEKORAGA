package com.skrg.sekoraga.service.query;

import com.skrg.sekoraga.repository.CExerciseVideoRepository;
import com.skrg.sekoraga.service.dto.CExerciseVideoDTO;
import com.skrg.sekoraga.service.mapper.CExerciseVideoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CExerciseVideoQueryService {
    private final CExerciseVideoRepository repository;
    private final CExerciseVideoMapper mapper;

    public CExerciseVideoQueryService(CExerciseVideoRepository repository, CExerciseVideoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<CExerciseVideoDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    public long count() {
        return repository.count();
    }
}
