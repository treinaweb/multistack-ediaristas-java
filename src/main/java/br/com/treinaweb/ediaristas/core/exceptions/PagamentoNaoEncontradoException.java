package br.com.treinaweb.ediaristas.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class PagamentoNaoEncontradoException extends EntityNotFoundException {

    public PagamentoNaoEncontradoException() {
        super("Pagamento não encontrado");
    }

    public PagamentoNaoEncontradoException(String message) {
        super(message);
    }

}
