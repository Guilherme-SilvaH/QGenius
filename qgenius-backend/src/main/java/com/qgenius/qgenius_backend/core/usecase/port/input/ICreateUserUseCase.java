package com.qgenius.qgenius_backend.core.usecase.port.input;

import com.qgenius.qgenius_backend.core.domain.entity.User;

public interface ICreateUserUseCase {
    User createUser(User user);
}