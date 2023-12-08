package br.com.treinaweb.ediaristas.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class EnderecoDiaristNaoEncontradoException extends EntityNotFoundException {

    public EnderecoDiaristNaoEncontradoException(String message) {
        super(message);
    }

}
