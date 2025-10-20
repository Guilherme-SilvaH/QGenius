package com.qgenius.qgenius_backend.infrastructure.adapter.output.persistence.mapper;

import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.domain.valueobject.Email;
import com.qgenius.qgenius_backend.infrastructure.adapter.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "email.value", target = "email")
    @Mapping(source = "createdAt", target = "createdAt")
    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);

    default Email stringToEmail(String value) {
        if (value == null) return null;
        return new Email(value);
    }

    default Instant map(LocalDateTime value) {
        if (value == null) return null;
        return value.atZone(ZoneId.systemDefault()).toInstant();
    }

    default LocalDateTime map(Instant value) {
        if (value == null) return null;
        return LocalDateTime.ofInstant(value, ZoneId.systemDefault());
    }
}
