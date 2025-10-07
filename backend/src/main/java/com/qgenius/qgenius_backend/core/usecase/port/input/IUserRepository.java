package com.qgenius.qgenius_backend.core.usecase.port.input;

import com.qgenius.qgenius_backend.core.domain.entity.User;

import java.util.Optional;


public interface IUserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
}

