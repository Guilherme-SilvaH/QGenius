// Local: com.qgenius.qgenius_backend.core.usecase.user

package com.qgenius.qgenius_backend.core.usecase.port.output.user;

import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.domain.exception.EmailAlreadyExistsException;
import com.qgenius.qgenius_backend.core.usecase.port.input.ICreateUserUseCase;
import com.qgenius.qgenius_backend.core.usecase.port.input.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase implements ICreateUserUseCase {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        String email = String.valueOf(user.getEmail());
        userRepository.findByEmail(email)
                .ifPresent(existingUser -> {
                    throw new EmailAlreadyExistsException(email);
                });

        String hashedPassword = passwordEncoder.encode(user.getPasswordHash());
        user.setPasswordHash(hashedPassword);
        return userRepository.save(user);
    }

}