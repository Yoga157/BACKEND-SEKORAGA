package com.skrg.sekoraga.service.mapper;

import com.skrg.sekoraga.domain.AdUserAuthority;
import com.skrg.sekoraga.service.dto.AdUserAuthorityDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AdUserAuthorityMapper {
    @Mapping(target = "userId", source = "id.userId")
    @Mapping(target = "authorityId", source = "id.authorityId")
    AdUserAuthorityDTO toDto(AdUserAuthority entity);

    @Mapping(target = "id.userId", source = "userId")
    @Mapping(target = "id.authorityId", source = "authorityId")
    AdUserAuthority toEntity(AdUserAuthorityDTO dto);
}
