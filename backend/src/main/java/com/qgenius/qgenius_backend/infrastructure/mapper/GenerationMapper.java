package com.qgenius.qgenius_backend.infrastructure.mapper;

import com.qgenius.qgenius_backend.core.domain.entity.Generation;
import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.domain.valueobject.Email;
import com.qgenius.qgenius_backend.infrastructure.entity.GenerationEntity;
import com.qgenius.qgenius_backend.infrastructure.entity.QuestionsEntity;
import com.qgenius.qgenius_backend.infrastructure.entity.UserEntity;
import org.mapstruct.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring", uses = {QuestionsMapper.class})
public interface GenerationMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user", target = "user", qualifiedByName = "userEntityToDomain")
    @Mapping(source = "theme", target = "theme")
    @Mapping(source = "difficulty", target = "difficulty")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "questionsList", target = "questionsList")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    Generation toDomain(GenerationEntity entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user", target = "user", qualifiedByName = "userDomainToEntity")
    @Mapping(source = "theme", target = "theme")
    @Mapping(source = "difficulty", target = "difficulty")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "questionsList", target = "questionsList", qualifiedByName = "mapQuestionsToEntities")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    GenerationEntity toEntity(Generation domain);

    @Named("instantToLocalDateTime")
    default LocalDateTime instantToLocalDateTime(Instant instant) {
        return instant != null ? LocalDateTime.ofInstant(instant, ZoneId.systemDefault()) : null;
    }

    @Named("localDateTimeToInstant")
    default Instant localDateTimeToInstant(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.atZone(ZoneId.systemDefault()).toInstant() : null;
    }

    @Named("userEntityToDomain")
    default User userEntityToDomain(UserEntity entity) {
        if (entity == null) return null;
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail() != null ? new Email(entity.getEmail()) : null)
                .plan(entity.getPlan())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Named("userDomainToEntity")
    default UserEntity userDomainToEntity(User user) {
        if (user == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail() != null ? user.getEmail().value() : null);
        entity.setPlan(user.getPlan());
        entity.setCreatedAt(user.getCreatedAt());
        return entity;
    }

    @Named("mapQuestionsToEntities")
    default List<QuestionsEntity> mapQuestionsToEntities(List<Questions> questions) {
        if (questions == null) return null;

        return questions.stream()
                .map(q -> {
                    QuestionsEntity qe = new QuestionsEntity();
                    qe.setId(q.getId());
                    qe.setQuestion(q.getQuestion());
                    qe.setContext(q.getContext());
                    if (q.getIdUser() != null) {
                        UserEntity userEntity = new UserEntity();
                        userEntity.setId(q.getIdUser());
                        qe.setUser(userEntity);
                    }
                    return qe;
                })
                .toList();
    }
}
