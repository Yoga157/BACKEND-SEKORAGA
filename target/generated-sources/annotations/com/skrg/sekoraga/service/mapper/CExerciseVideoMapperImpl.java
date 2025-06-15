package com.skrg.sekoraga.service.mapper;

import com.skrg.sekoraga.domain.CExerciseCategory;
import com.skrg.sekoraga.domain.CExerciseVideo;
import com.skrg.sekoraga.service.dto.CExerciseVideoDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T15:24:42+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (OpenLogic)"
)
@Component
public class CExerciseVideoMapperImpl implements CExerciseVideoMapper {

    @Override
    public CExerciseVideoDTO toDto(CExerciseVideo entity) {
        if ( entity == null ) {
            return null;
        }

        CExerciseVideoDTO cExerciseVideoDTO = new CExerciseVideoDTO();

        cExerciseVideoDTO.setCategoryId( entityCategoryCategoryId( entity ) );
        cExerciseVideoDTO.setVideoId( entity.getVideoId() );
        cExerciseVideoDTO.setTitle( entity.getTitle() );
        cExerciseVideoDTO.setDescription( entity.getDescription() );
        cExerciseVideoDTO.setYoutubeUrl( entity.getYoutubeUrl() );
        cExerciseVideoDTO.setThumbnailUrl( entity.getThumbnailUrl() );

        return cExerciseVideoDTO;
    }

    @Override
    public CExerciseVideo toEntity(CExerciseVideoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CExerciseVideo cExerciseVideo = new CExerciseVideo();

        cExerciseVideo.setVideoId( dto.getVideoId() );
        cExerciseVideo.setTitle( dto.getTitle() );
        cExerciseVideo.setDescription( dto.getDescription() );
        cExerciseVideo.setYoutubeUrl( dto.getYoutubeUrl() );
        cExerciseVideo.setThumbnailUrl( dto.getThumbnailUrl() );

        cExerciseVideo.setCategory( categoryFromId(dto.getCategoryId()) );

        return cExerciseVideo;
    }

    private Long entityCategoryCategoryId(CExerciseVideo cExerciseVideo) {
        CExerciseCategory category = cExerciseVideo.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getCategoryId();
    }
}
