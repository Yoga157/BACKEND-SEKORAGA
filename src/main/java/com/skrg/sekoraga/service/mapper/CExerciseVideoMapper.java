package com.skrg.sekoraga.service.mapper;

import com.skrg.sekoraga.domain.CExerciseVideo;
import com.skrg.sekoraga.service.dto.CExerciseVideoDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CExerciseVideoMapper {
    @Mapping(target = "categoryId", source = "category.categoryId")
    CExerciseVideoDTO toDto(CExerciseVideo entity);

    @Mapping(target = "category", expression = "java(categoryFromId(dto.getCategoryId()))")
    CExerciseVideo toEntity(CExerciseVideoDTO dto);

    default com.skrg.sekoraga.domain.CExerciseCategory categoryFromId(Long id) {
        if (id == null)
            return null;
        com.skrg.sekoraga.domain.CExerciseCategory cat = new com.skrg.sekoraga.domain.CExerciseCategory();
        cat.setCategoryId(id);
        return cat;
    }
}
