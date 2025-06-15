package com.skrg.sekoraga.service;

import com.skrg.sekoraga.repository.CExerciseCategoryRepository;
import com.skrg.sekoraga.service.dto.CExerciseCategoryDTO;
import com.skrg.sekoraga.service.mapper.CExerciseCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CExerciseCategoryService {
    private final CExerciseCategoryRepository repository;
    private final CExerciseCategoryMapper mapper;

    public CExerciseCategoryService(CExerciseCategoryRepository repository, CExerciseCategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<CExerciseCategoryDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public List<CExerciseCategoryDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public CExerciseCategoryDTO save(CExerciseCategoryDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // ...CRUD lain bisa ditambah di sini
}
