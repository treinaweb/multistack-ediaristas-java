package br.com.treinaweb.ediaristas.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class DiariaNaoEncontradaException extends EntityNotFoundException {

    public DiariaNaoEncontradaException(String message) {
        super(message);
    }

}
