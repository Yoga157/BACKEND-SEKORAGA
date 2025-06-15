package com.skrg.sekoraga.service.query;

import com.skrg.sekoraga.domain.CExerciseCategory;
import com.skrg.sekoraga.repository.CExerciseCategoryRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CExerciseCategoryQueryService {
    private final CExerciseCategoryRepository repository;

    public CExerciseCategoryQueryService(CExerciseCategoryRepository repository) {
        this.repository = repository;
    }

    public List<CExerciseCategory> findByCriteria(Specification<CExerciseCategory> spec) {
        return repository.findAll(spec);
    }
}
