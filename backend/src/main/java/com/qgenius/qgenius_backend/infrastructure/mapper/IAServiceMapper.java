package com.qgenius.qgenius_backend.infrastructure.mapper;

import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import com.qgenius.qgenius_backend.infrastructure.client.dto.GeneratedQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, LocalDateTime.class})
public interface IAServiceMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(source = "question", target = "question")
    @Mapping(source = "context", target = "context")
    @Mapping(target = "themes", ignore = true)
    @Mapping(target = "themesCustoms", ignore = true)
    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "generation", ignore = true)
    Questions toDomain(GeneratedQuestion generatedQuestion);
}