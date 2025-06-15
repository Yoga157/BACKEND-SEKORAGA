package com.skrg.sekoraga.repository;

import com.skrg.sekoraga.domain.CExerciseVideo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CExerciseVideoRepository
        extends JpaRepository<CExerciseVideo, Long>, JpaSpecificationExecutor<CExerciseVideo> {
}
