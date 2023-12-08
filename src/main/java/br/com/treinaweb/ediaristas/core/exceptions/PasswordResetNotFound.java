package br.com.treinaweb.ediaristas.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class PasswordResetNotFound extends EntityNotFoundException {

    public PasswordResetNotFound() {}

    public PasswordResetNotFound(String message) {
        super(message);
    }

}
