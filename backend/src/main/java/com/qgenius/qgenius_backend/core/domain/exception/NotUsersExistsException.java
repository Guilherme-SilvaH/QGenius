package com.qgenius.qgenius_backend.core.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NotUsersExistsException extends RuntimeException {
    public NotUsersExistsException() {
        super("Nenhum usuário encontrado na base de dados.");
    }
}
