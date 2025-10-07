package com.qgenius.qgenius_backend.infrastructure.adapter.output.persistence.mapper;

import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.domain.valueobject.Email;
import com.qgenius.qgenius_backend.infrastructure.adapter.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "email.value", target = "email")
    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);

    default Email stringToEmail(String value) {
        if (value == null) {
            return null;
        }
        return new Email(value);
    }
}