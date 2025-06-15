package com.skrg.sekoraga.service.mapper;

import com.skrg.sekoraga.domain.CExerciseCategory;
import com.skrg.sekoraga.domain.CAttachment;
import com.skrg.sekoraga.service.dto.CExerciseCategoryDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CExerciseCategoryMapper {
    @Mapping(target = "attachmentId", source = "attachment.attachmentId")
    CExerciseCategoryDTO toDto(CExerciseCategory entity);

    @Mapping(target = "attachment", expression = "java(attachmentFromId(dto.getAttachmentId()))")
    CExerciseCategory toEntity(CExerciseCategoryDTO dto);

    default CAttachment attachmentFromId(Long id) {
        if (id == null)
            return null;
        CAttachment att = new CAttachment();
        att.setAttachmentId(id);
        return att;
    }
}
