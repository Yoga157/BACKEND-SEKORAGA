package com.skrg.sekoraga.repository;

import com.skrg.sekoraga.domain.CActivitySchedule;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CActivityScheduleRepository
                extends JpaRepository<CActivitySchedule, Integer>, JpaSpecificationExecutor<CActivitySchedule> {
        List<CActivitySchedule> findByUser_UserId(Long userId);
}
