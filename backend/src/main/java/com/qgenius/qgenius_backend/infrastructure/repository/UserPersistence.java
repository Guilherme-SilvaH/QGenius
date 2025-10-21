package com.qgenius.qgenius_backend.infrastructure.repository;

import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IUserRepository;
import com.qgenius.qgenius_backend.infrastructure.entity.UserEntity;
import com.qgenius.qgenius_backend.infrastructure.mapper.UserMapper;
import com.qgenius.qgenius_backend.infrastructure.repository.interfaces.SpringDataUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserPersistence implements IUserRepository {

    private final SpringDataUserRepository springDataUserRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public User save(User user) {
        String email = user.getEmail().value();
        log.debug("Saving user: {}", email);
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = springDataUserRepository.save(userEntity);
        log.info("User saved with ID: {}", savedEntity.getId());
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.debug("Finding user by email: {}", email);
        return springDataUserRepository.findByEmail(email)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        log.debug("Finding user by ID: {}", id);
        return springDataUserRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.debug("Checking if email exists: {}", email);
        return springDataUserRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        log.info("Deleting user with ID: {}", id);
        springDataUserRepository.deleteById(id);
    }

}