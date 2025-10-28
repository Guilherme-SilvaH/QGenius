package com.qgenius.qgenius_backend.infrastructure.mapper;

import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import com.qgenius.qgenius_backend.core.domain.entity.ThemesCustoms;
import com.qgenius.qgenius_backend.infrastructure.rest.request.QuestionsRequest;
import com.qgenius.qgenius_backend.infrastructure.rest.response.QuestionsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring",
        imports = {UUID.class, LocalDateTime.class})
public interface QuestionsWebMapper {


    @Mapping(source = "themesCustoms", target = "themesCustoms", qualifiedByName = "themesCustomsToResponse")
    QuestionsResponse toResponse(Questions questions);

    @Mapping(source = "customTheme", target = "themesCustoms", qualifiedByName = "requestToThemesCustoms")
    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Questions toDomain(QuestionsRequest request);

    @Named("themesCustomsToResponse")
    default ThemesCustoms themesCustomsToResponse(ThemesCustoms value) {
        return value != null ? new ThemesCustoms(value.getCustomThemes()) : null;
    }

    @Named("requestToThemesCustoms")
    default ThemesCustoms requestToThemesCustoms(String customTheme) {
        return customTheme != null ? new ThemesCustoms(customTheme) : null;
    }
}
