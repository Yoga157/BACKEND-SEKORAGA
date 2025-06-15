package com.skrg.sekoraga.service.mapper;

import com.skrg.sekoraga.domain.AdUser;
import com.skrg.sekoraga.domain.CActivitySchedule;
import com.skrg.sekoraga.service.dto.CActivityScheduleDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T15:24:42+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (OpenLogic)"
)
@Component
public class CActivityScheduleMapperImpl implements CActivityScheduleMapper {

    @Override
    public CActivityScheduleDTO toDto(CActivitySchedule entity) {
        if ( entity == null ) {
            return null;
        }

        CActivityScheduleDTO cActivityScheduleDTO = new CActivityScheduleDTO();

        cActivityScheduleDTO.setUserId( entityUserUserId( entity ) );
        cActivityScheduleDTO.setScheduleId( entity.getScheduleId() );
        cActivityScheduleDTO.setDayOfWeek( entity.getDayOfWeek() );
        cActivityScheduleDTO.setActivityName( entity.getActivityName() );
        cActivityScheduleDTO.setPointValue( entity.getPointValue() );

        return cActivityScheduleDTO;
    }

    @Override
    public CActivitySchedule toEntity(CActivityScheduleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CActivitySchedule cActivitySchedule = new CActivitySchedule();

        cActivitySchedule.setScheduleId( dto.getScheduleId() );
        cActivitySchedule.setDayOfWeek( dto.getDayOfWeek() );
        cActivitySchedule.setActivityName( dto.getActivityName() );
        cActivitySchedule.setPointValue( dto.getPointValue() );

        cActivitySchedule.setUser( dto.getUserId() != null ? new com.skrg.sekoraga.domain.AdUser(dto.getUserId()) : null );

        return cActivitySchedule;
    }

    private Long entityUserUserId(CActivitySchedule cActivitySchedule) {
        AdUser user = cActivitySchedule.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getUserId();
    }
}
