package com.qgenius.qgenius_backend.core.usecase.repository.users;

import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.domain.exception.EmailAlreadyExistsException;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.ICreateUserUseCase;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUserUseCase implements ICreateUserUseCase {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(User user) {

        String email = user.getEmail().value();
        log.debug("Creating user with email: {}", email);

        if (userRepository.existsByEmail(email)) {
            log.warn("Attempt to create user with existing email: {}", email);
            throw new EmailAlreadyExistsException(email);
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User savedUser = userRepository.save(user);
        log.info("User created successfully with id: {}", savedUser.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}