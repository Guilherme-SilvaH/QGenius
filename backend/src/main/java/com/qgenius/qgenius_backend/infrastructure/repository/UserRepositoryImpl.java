package com.qgenius.qgenius_backend.infrastructure.repository;

import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IUserRepository;
import com.qgenius.qgenius_backend.infrastructure.entity.UserEntity;
import com.qgenius.qgenius_backend.infrastructure.mapper.UserMapper;
import com.qgenius.qgenius_backend.infrastructure.repository.interfaces.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
public class UserRepositoryImpl implements IUserRepository {

    private final UserJpaRepository userRepository;
    private final UserMapper userMapper;

    public UserRepositoryImpl(@Lazy UserJpaRepository userRepository,
                              UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public User save(User user) {
        String email = user.getEmail().value();
        log.debug("Saving user: {}", email);
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = userRepository.save(userEntity);
        log.info("User saved with ID: {}", savedEntity.getId());
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("Finding user by email: {}", email);
        return userRepository.findByEmail(email)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        log.info("Finding user by ID: {}", id);
        return userRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.info("Checking if email exists: {}", email);
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        log.info("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
    }
}