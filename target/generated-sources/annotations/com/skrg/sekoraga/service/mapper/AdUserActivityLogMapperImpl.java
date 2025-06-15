package com.skrg.sekoraga.service.mapper;

import com.skrg.sekoraga.domain.AdUser;
import com.skrg.sekoraga.domain.AdUserActivityLog;
import com.skrg.sekoraga.domain.CActivitySchedule;
import com.skrg.sekoraga.service.dto.AdUserActivityLogDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T15:24:42+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (OpenLogic)"
)
@Component
public class AdUserActivityLogMapperImpl implements AdUserActivityLogMapper {

    @Override
    public AdUserActivityLogDTO toDto(AdUserActivityLog entity) {
        if ( entity == null ) {
            return null;
        }

        AdUserActivityLogDTO adUserActivityLogDTO = new AdUserActivityLogDTO();

        adUserActivityLogDTO.setUserId( entityUserUserId( entity ) );
        adUserActivityLogDTO.setScheduleId( entityScheduleScheduleId( entity ) );
        adUserActivityLogDTO.setLogId( entity.getLogId() );
        adUserActivityLogDTO.setActivityDate( entity.getActivityDate() );
        adUserActivityLogDTO.setNotes( entity.getNotes() );
        adUserActivityLogDTO.setVerifiedByTeacher( entity.getVerifiedByTeacher() );
        adUserActivityLogDTO.setCreatedDate( entity.getCreatedDate() );

        return adUserActivityLogDTO;
    }

    @Override
    public AdUserActivityLog toEntity(AdUserActivityLogDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AdUserActivityLog adUserActivityLog = new AdUserActivityLog();

        adUserActivityLog.setLogId( dto.getLogId() );
        adUserActivityLog.setActivityDate( dto.getActivityDate() );
        adUserActivityLog.setNotes( dto.getNotes() );
        adUserActivityLog.setVerifiedByTeacher( dto.getVerifiedByTeacher() );
        adUserActivityLog.setCreatedDate( dto.getCreatedDate() );

        adUserActivityLog.setUser( dto.getUserId() != null ? new com.skrg.sekoraga.domain.AdUser(dto.getUserId()) : null );
        adUserActivityLog.setSchedule( dto.getScheduleId() != null ? new com.skrg.sekoraga.domain.CActivitySchedule(dto.getScheduleId()) : null );

        return adUserActivityLog;
    }

    private Long entityUserUserId(AdUserActivityLog adUserActivityLog) {
        AdUser user = adUserActivityLog.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getUserId();
    }

    private Integer entityScheduleScheduleId(AdUserActivityLog adUserActivityLog) {
        CActivitySchedule schedule = adUserActivityLog.getSchedule();
        if ( schedule == null ) {
            return null;
        }
        return schedule.getScheduleId();
    }
}
