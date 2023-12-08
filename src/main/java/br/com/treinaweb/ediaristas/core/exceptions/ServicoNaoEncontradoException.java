package br.com.treinaweb.ediaristas.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ServicoNaoEncontradoException extends EntityNotFoundException {

    public ServicoNaoEncontradoException(String message) {
        super(message);
    }
    
}
