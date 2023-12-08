package br.com.treinaweb.ediaristas.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class CidadeAtendidaNaoEncontradaException extends EntityNotFoundException {

    public CidadeAtendidaNaoEncontradaException(String message) {
        super(message);
    }

}
