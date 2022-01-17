package br.com.treinaweb.ediaristas.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class CidadeAtendidaNaoEncontradaException extends EntityNotFoundException {

    public CidadeAtendidaNaoEncontradaException(String message) {
        super(message);
    }

}
