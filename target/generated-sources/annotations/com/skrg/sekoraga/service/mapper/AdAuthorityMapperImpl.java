package com.skrg.sekoraga.service.mapper;

import com.skrg.sekoraga.domain.AdAuthority;
import com.skrg.sekoraga.domain.dto.AdAuthorityDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T15:24:42+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (OpenLogic)"
)
@Component
public class AdAuthorityMapperImpl implements AdAuthorityMapper {

    @Override
    public AdAuthorityDTO toDto(AdAuthority entity) {
        if ( entity == null ) {
            return null;
        }

        AdAuthorityDTO adAuthorityDTO = new AdAuthorityDTO();

        adAuthorityDTO.setId( AdAuthorityMapper.longToString( entity.getAuthorityId() ) );
        adAuthorityDTO.setName( entity.getName() );

        return adAuthorityDTO;
    }

    @Override
    public AdAuthority toEntity(AdAuthorityDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AdAuthority adAuthority = new AdAuthority();

        adAuthority.setAuthorityId( AdAuthorityMapper.stringToLong( dto.getId() ) );
        adAuthority.setName( dto.getName() );

        return adAuthority;
    }
}
