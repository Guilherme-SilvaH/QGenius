
package com.qgenius.qgenius_backend.infrastructure.mapper;

import com.qgenius.qgenius_backend.core.domain.entity.Generation;
import com.qgenius.qgenius_backend.infrastructure.rest.request.GenerationRequest;
import com.qgenius.qgenius_backend.infrastructure.rest.response.GenerationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring",
        uses = {QuestionsWebMapper.class},
        imports = {UUID.class, LocalDateTime.class})
public interface GenerationWebMapper {

    @Mapping(source = "questionsList", target = "questions")
    GenerationResponse toResponse(Generation generation);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "questionsList", ignore = true)
    Generation toDomain(GenerationRequest request);
}