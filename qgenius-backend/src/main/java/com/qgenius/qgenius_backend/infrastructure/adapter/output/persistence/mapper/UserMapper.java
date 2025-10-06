package com.qgenius.qgenius_backend.infrastructure.adapter.output.persistence.mapper;

import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.infrastructure.adapter.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(User user);
    User toDomain(UserEntity userEntity);

}