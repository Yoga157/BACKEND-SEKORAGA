package com.skrg.sekoraga.service.mapper;

import com.skrg.sekoraga.domain.AdUserActivityLog;
import com.skrg.sekoraga.service.dto.AdUserActivityLogDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AdUserActivityLogMapper {
    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "scheduleId", source = "schedule.scheduleId")
    AdUserActivityLogDTO toDto(AdUserActivityLog entity);

    @Mapping(target = "user", expression = "java(dto.getUserId() != null ? new com.skrg.sekoraga.domain.AdUser(dto.getUserId()) : null)")
    @Mapping(target = "schedule", expression = "java(dto.getScheduleId() != null ? new com.skrg.sekoraga.domain.CActivitySchedule(dto.getScheduleId()) : null)")
    AdUserActivityLog toEntity(AdUserActivityLogDTO dto);
}
