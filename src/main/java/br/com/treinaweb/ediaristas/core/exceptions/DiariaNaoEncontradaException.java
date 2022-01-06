package br.com.treinaweb.ediaristas.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class DiariaNaoEncontradaException extends EntityNotFoundException {

    public DiariaNaoEncontradaException(String message) {
        super(message);
    }

}
