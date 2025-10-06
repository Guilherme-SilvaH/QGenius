package com.qgenius.qgenius_backend.core.usecase.user;

import com.qgenius.qgenius_backend.core.domain.entity.User;

public interface ICreateUserUseCase {
    User createUser(User user);
}