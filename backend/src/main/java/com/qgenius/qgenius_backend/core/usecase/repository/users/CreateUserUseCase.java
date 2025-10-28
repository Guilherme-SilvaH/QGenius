package com.qgenius.qgenius_backend.core.usecase.repository.users;

import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.domain.exception.EmailAlreadyExistsException;
import com.qgenius.qgenius_backend.core.domain.exception.NotUsersExistsException;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.ICreateUserUseCase;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
public class CreateUserUseCase implements ICreateUserUseCase {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase(@Lazy IUserRepository userRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        String email = user.getEmail().value();
        log.info("Creating user with email: {}", email);
        if (userRepository.existsByEmail(email)) {
            log.warn("Attempt to create user with existing email: {}", email);
            throw new EmailAlreadyExistsException(email);
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User savedUser = userRepository.save(user);
        log.info("User created successfully with id: {}", savedUser.getId());

        return savedUser;
    }

}