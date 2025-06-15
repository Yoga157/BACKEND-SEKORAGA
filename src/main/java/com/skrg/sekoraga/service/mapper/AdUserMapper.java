package com.skrg.sekoraga.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.skrg.sekoraga.domain.AdUser;
import com.skrg.sekoraga.domain.dto.AdUserDTO;

@Mapper(componentModel = "spring", uses = { AdAuthorityMapper.class })
public interface AdUserMapper {
    @Mapping(source = "userId", target = "id", qualifiedByName = "adUserLongToString")
    @Mapping(source = "password", target = "password")
    AdUserDTO toDto(AdUser entity);

    @Mapping(source = "id", target = "userId", qualifiedByName = "adUserStringToLong")
    @Mapping(source = "password", target = "password")
    AdUser toEntity(AdUserDTO dto);

    @Named("adUserLongToString")
    static String adUserLongToString(Long id) {
        return id == null ? null : id.toString();
    }

    @Named("adUserStringToLong")
    static Long adUserStringToLong(String id) {
        if (id == null || id.isEmpty())
            return null;
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID format: " + id, e);
        }
    }

}
