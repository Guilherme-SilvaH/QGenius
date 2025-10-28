package com.qgenius.qgenius_backend.core.usecase.repository.interfaces;
import com.qgenius.qgenius_backend.core.domain.entity.User;
import java.util.List;

public interface IFindAllUsersUseCase {
    List<User> findAllUsers();
}
