package com.skrg.sekoraga.repository;

import com.skrg.sekoraga.domain.AdUserActivityLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdUserActivityLogRepository
        extends JpaRepository<AdUserActivityLog, Integer>, JpaSpecificationExecutor<AdUserActivityLog> {
    List<AdUserActivityLog> findByUser_UserId(Long userId);
}
