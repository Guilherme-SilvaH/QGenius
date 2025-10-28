package com.qgenius.qgenius_backend.core.usecase.repository.users;

import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.domain.exception.NotUsersExistsException;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IUserRepository;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IFindAllUsersUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UsersUseCase implements IFindAllUsersUseCase {

    private final IUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        var users = userRepository.findAllUsers();
        log.info("Total de usuários cadastrados no sistema: {}", users.size());
        if (users.isEmpty()) {
            throw new NotUsersExistsException();
        }
        return users;
    }
}
