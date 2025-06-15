package com.skrg.sekoraga.repository;

import com.skrg.sekoraga.domain.CExerciseCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CExerciseCategoryRepository
        extends JpaRepository<CExerciseCategory, Long>, JpaSpecificationExecutor<CExerciseCategory> {
}
