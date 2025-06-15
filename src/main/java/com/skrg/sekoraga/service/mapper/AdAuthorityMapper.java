package com.skrg.sekoraga.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.skrg.sekoraga.domain.AdAuthority;
import com.skrg.sekoraga.domain.dto.AdAuthorityDTO;

@Mapper(componentModel = "spring")
public interface AdAuthorityMapper {
    @Mapping(source = "authorityId", target = "id", qualifiedByName = "longToString")
    AdAuthorityDTO toDto(AdAuthority entity);

    @Mapping(source = "id", target = "authorityId", qualifiedByName = "stringToLong")
    AdAuthority toEntity(AdAuthorityDTO dto);

    @Named("longToString")
    public static String longToString(Long id) {
        return id == null ? null : id.toString();
    }

    @Named("stringToLong")
    public static Long stringToLong(String id) {
        if (id == null || id.isEmpty())
            return null;
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID format: " + id, e);
        }
    }
}
