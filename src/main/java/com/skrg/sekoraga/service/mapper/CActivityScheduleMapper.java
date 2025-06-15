package com.skrg.sekoraga.service.mapper;

import com.skrg.sekoraga.domain.CActivitySchedule;
import com.skrg.sekoraga.service.dto.CActivityScheduleDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CActivityScheduleMapper {
    @Mapping(target = "userId", source = "user.userId")
    CActivityScheduleDTO toDto(CActivitySchedule entity);

    @Mapping(target = "user", expression = "java(dto.getUserId() != null ? new com.skrg.sekoraga.domain.AdUser(dto.getUserId()) : null)")
    CActivitySchedule toEntity(CActivityScheduleDTO dto);
}
