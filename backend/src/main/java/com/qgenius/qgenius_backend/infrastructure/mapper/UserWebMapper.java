package com.qgenius.qgenius_backend.infrastructure.mapper;

import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.domain.valueobject.Email;
import com.qgenius.qgenius_backend.core.domain.valueobject.Plan;
import com.qgenius.qgenius_backend.infrastructure.rest.request.SignupRequest;
import com.qgenius.qgenius_backend.infrastructure.rest.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",
        imports = {UUID.class, LocalDateTime.class, Plan.class})
public interface UserWebMapper {

    @Mapping(source = "email", target = "email", qualifiedByName = "stringToEmailVo")
    @Mapping(source = "password", target = "password")
    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "plan", expression = "java(Plan.FREE)")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    User toDomain(SignupRequest request);


    @Mapping(source = "email", target = "email", qualifiedByName = "emailVoToString")
    UserResponse toResponse(User user);

    @Named("stringToEmailVo")
    default Email stringToEmailVo(String email) {
        return email != null ? new Email(email) : null;
    }

    @Named("emailVoToString")
    default String emailVoToString(Email email) {
        return email != null ? email.value() : null;
    }
}