package com.qgenius.qgenius_backend.infrastructure.mapper;

import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.domain.valueobject.Email;
import com.qgenius.qgenius_backend.core.domain.valueobject.Plan;
import com.qgenius.qgenius_backend.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", imports = {ZoneOffset.class})
public interface UserMapper {

    @Mapping(source = "passwordHash", target = "password")
    @Mapping(source = "email", target = "email", qualifiedByName = "stringToEmailVo")
    @Mapping(source = "plan", target = "plan", qualifiedByName = "stringToPlanEnum")
    User toDomain(UserEntity entity);

    @Mapping(source = "password", target = "passwordHash")
    @Mapping(source = "email", target = "email", qualifiedByName = "emailVoToString")
    @Mapping(source = "plan", target = "plan", qualifiedByName = "planEnumToString")
    UserEntity toEntity(User user);


    @Named("stringToEmailVo")
    default Email stringToEmailVo(String email) {
        return email != null ? new Email(email) : null;
    }

    @Named("emailVoToString")
    default String emailVoToString(Email email) {
        return email != null ? email.value() : null;
    }

    @Named("stringToPlanEnum")
    default Plan stringToPlanEnum(String plan) {
        return plan != null ? Plan.valueOf(plan) : null;
    }

    @Named("planEnumToString")
    default String planEnumToString(Plan plan) {
        return plan != null ? plan.name() : null;
    }
    default LocalDateTime instantToLocalDateTime(Instant instant) {
        return (instant != null) ? LocalDateTime.ofInstant(instant, ZoneOffset.UTC) : null;
    }

    default Instant localDateTimeToInstant(LocalDateTime localDateTime) {
        return (localDateTime != null) ? localDateTime.toInstant(ZoneOffset.UTC) : null;
    }
}