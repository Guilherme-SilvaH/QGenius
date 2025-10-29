package com.qgenius.qgenius_backend.infrastructure.mapper;

import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import com.qgenius.qgenius_backend.core.domain.entity.Themes;
import com.qgenius.qgenius_backend.core.domain.entity.ThemesCustoms;
import com.qgenius.qgenius_backend.infrastructure.entity.QuestionsEntity;
import com.qgenius.qgenius_backend.infrastructure.entity.ThemeEntity;
import com.qgenius.qgenius_backend.infrastructure.entity.ThemesCustomsEntity;
import com.qgenius.qgenius_backend.infrastructure.entity.UserEntity;
import org.mapstruct.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface QuestionsMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "question", target = "question")
    @Mapping(source = "context", target = "context")
    @Mapping(source = "theme", target = "themes", qualifiedByName = "themeEntityToEnum")
    @Mapping(source = "customTheme", target = "themesCustoms", qualifiedByName = "themesCustomsEntityToDomain")
    @Mapping(target = "generation", ignore = true)
    @Mapping(source = "user.id", target = "idUser")
    @Mapping(source = "createdAt", target = "createdAt")
    Questions toDomain(QuestionsEntity entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "question", target = "question")
    @Mapping(source = "context", target = "context")
    @Mapping(source = "themes", target = "theme", qualifiedByName = "themesEnumToEntity")
    @Mapping(source = "themesCustoms", target = "customTheme", qualifiedByName = "themesCustomsDomainToEntity")
    @Mapping(source = "idUser", target = "user", qualifiedByName = "uuidToUserEntity")
    @Mapping(target = "generation", ignore = true)
    @Mapping(source = "createdAt", target = "createdAt")
    QuestionsEntity toEntity(Questions domain);

    @Named("themeEntityToEnum")
    default Themes themeEntityToEnum(ThemeEntity themeEntity) {
        if (themeEntity == null) return null;
        return Themes.valueOf(themeEntity.getCode().toUpperCase());
    }
    @Named("instantToLocalDateTime")
    default LocalDateTime instantToLocalDateTime(Instant instant) {
        return instant != null ? LocalDateTime.ofInstant(instant, ZoneId.systemDefault()) : null;
    }

    @Named("localDateTimeToInstant")
    default Instant localDateTimeToInstant(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.atZone(ZoneId.systemDefault()).toInstant() : null;
    }

    @Named("themesEnumToEntity")
    default ThemeEntity themesEnumToEntity(Themes themes) {
        if (themes == null) return null;
        ThemeEntity theme = new ThemeEntity();
        theme.setCode(themes.name());
        return theme;
    }

    @Named("themesCustomsEntityToDomain")
    default ThemesCustoms themesCustomsEntityToDomain(ThemesCustomsEntity entity) {
        if (entity == null) return null;
        return new ThemesCustoms(entity.getCustomThemes());
    }

    @Named("themesCustomsDomainToEntity")
    default ThemesCustomsEntity themesCustomsDomainToEntity(ThemesCustoms domain) {
        if (domain == null) return null;
        ThemesCustomsEntity entity = new ThemesCustomsEntity();
        entity.setCustomThemes(domain.getCustomThemes());
        return entity;
    }

    @Named("uuidToUserEntity")
    default UserEntity uuidToUserEntity(UUID id) {
        if (id == null) return null;
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }
}

