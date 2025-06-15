package com.skrg.sekoraga.service.mapper;

import com.skrg.sekoraga.domain.AdUserAuthority;
import com.skrg.sekoraga.domain.AdUserAuthorityId;
import com.skrg.sekoraga.service.dto.AdUserAuthorityDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T15:24:42+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (OpenLogic)"
)
@Component
public class AdUserAuthorityMapperImpl implements AdUserAuthorityMapper {

    @Override
    public AdUserAuthorityDTO toDto(AdUserAuthority entity) {
        if ( entity == null ) {
            return null;
        }

        AdUserAuthorityDTO adUserAuthorityDTO = new AdUserAuthorityDTO();

        adUserAuthorityDTO.setUserId( entityIdUserId( entity ) );
        adUserAuthorityDTO.setAuthorityId( entityIdAuthorityId( entity ) );
        adUserAuthorityDTO.setUuid( entity.getUuid() );

        return adUserAuthorityDTO;
    }

    @Override
    public AdUserAuthority toEntity(AdUserAuthorityDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AdUserAuthority adUserAuthority = new AdUserAuthority();

        adUserAuthority.setId( adUserAuthorityDTOToAdUserAuthorityId( dto ) );
        adUserAuthority.setUuid( dto.getUuid() );

        return adUserAuthority;
    }

    private Long entityIdUserId(AdUserAuthority adUserAuthority) {
        AdUserAuthorityId id = adUserAuthority.getId();
        if ( id == null ) {
            return null;
        }
        return id.getUserId();
    }

    private Long entityIdAuthorityId(AdUserAuthority adUserAuthority) {
        AdUserAuthorityId id = adUserAuthority.getId();
        if ( id == null ) {
            return null;
        }
        return id.getAuthorityId();
    }

    protected AdUserAuthorityId adUserAuthorityDTOToAdUserAuthorityId(AdUserAuthorityDTO adUserAuthorityDTO) {
        if ( adUserAuthorityDTO == null ) {
            return null;
        }

        AdUserAuthorityId adUserAuthorityId = new AdUserAuthorityId();

        adUserAuthorityId.setUserId( adUserAuthorityDTO.getUserId() );
        adUserAuthorityId.setAuthorityId( adUserAuthorityDTO.getAuthorityId() );

        return adUserAuthorityId;
    }
}
