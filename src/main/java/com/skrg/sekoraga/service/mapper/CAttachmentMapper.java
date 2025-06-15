package com.skrg.sekoraga.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.skrg.sekoraga.domain.CAttachment;
import com.skrg.sekoraga.domain.dto.CAttachmentDTO;

@Mapper(componentModel = "spring")
public interface CAttachmentMapper {
    @Mapping(source = "attachmentId", target = "id", qualifiedByName = "longToString")
    CAttachmentDTO toDto(CAttachment entity);

    @Mapping(source = "id", target = "attachmentId", qualifiedByName = "stringToLong")
    CAttachment toEntity(CAttachmentDTO dto);

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
