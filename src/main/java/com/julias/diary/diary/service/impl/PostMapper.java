package com.julias.diary.diary.service.impl;

import com.julias.diary.diary.io.entity.PostEntity;
import com.julias.diary.diary.shared.dto.PostDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel= "Spring")
public interface PostMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePostFromDto(PostDto postDto, @MappingTarget PostEntity postEntity);
}
