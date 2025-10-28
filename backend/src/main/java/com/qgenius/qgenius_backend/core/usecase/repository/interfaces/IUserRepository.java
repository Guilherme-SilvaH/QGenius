package com.qgenius.qgenius_backend.core.usecase.repository.interfaces;

import com.qgenius.qgenius_backend.core.domain.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    User save(User user);
    List<User> findAllUsers();
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
    boolean existsByEmail(String email);
    void delete(UUID id);
}