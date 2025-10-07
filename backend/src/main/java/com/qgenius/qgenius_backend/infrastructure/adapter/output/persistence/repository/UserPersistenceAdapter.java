package com.qgenius.qgenius_backend.infrastructure.adapter.output.persistence.repository;

import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.usecase.port.input.IUserRepository;
import com.qgenius.qgenius_backend.infrastructure.adapter.output.persistence.entity.UserEntity;
import com.qgenius.qgenius_backend.infrastructure.adapter.output.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements IUserRepository {

    private final UserRepository springDataUserRepository;

    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = springDataUserRepository.save(userEntity);
        return userMapper.toDomain(savedEntity);
    }


    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userEntityOptional = springDataUserRepository.findByEmail(email);
        return userEntityOptional.map(userMapper::toDomain);
    }

}