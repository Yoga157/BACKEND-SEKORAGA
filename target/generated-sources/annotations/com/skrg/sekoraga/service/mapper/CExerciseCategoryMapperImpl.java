package com.skrg.sekoraga.service.mapper;

import com.skrg.sekoraga.domain.CAttachment;
import com.skrg.sekoraga.domain.CExerciseCategory;
import com.skrg.sekoraga.service.dto.CExerciseCategoryDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T13:49:28+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (OpenLogic)"
)
@Component
public class CExerciseCategoryMapperImpl implements CExerciseCategoryMapper {

    @Override
    public CExerciseCategoryDTO toDto(CExerciseCategory entity) {
        if ( entity == null ) {
            return null;
        }

        CExerciseCategoryDTO cExerciseCategoryDTO = new CExerciseCategoryDTO();

        cExerciseCategoryDTO.setAttachmentId( entityAttachmentAttachmentId( entity ) );
        cExerciseCategoryDTO.setCategoryId( entity.getCategoryId() );
        cExerciseCategoryDTO.setTitle( entity.getTitle() );
        cExerciseCategoryDTO.setDescription( entity.getDescription() );

        return cExerciseCategoryDTO;
    }

    @Override
    public CExerciseCategory toEntity(CExerciseCategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CExerciseCategory cExerciseCategory = new CExerciseCategory();

        cExerciseCategory.setCategoryId( dto.getCategoryId() );
        cExerciseCategory.setTitle( dto.getTitle() );
        cExerciseCategory.setDescription( dto.getDescription() );

        cExerciseCategory.setAttachment( attachmentFromId(dto.getAttachmentId()) );

        return cExerciseCategory;
    }

    private Long entityAttachmentAttachmentId(CExerciseCategory cExerciseCategory) {
        CAttachment attachment = cExerciseCategory.getAttachment();
        if ( attachment == null ) {
            return null;
        }
        return attachment.getAttachmentId();
    }
}
