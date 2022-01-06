package br.com.treinaweb.ediaristas.core.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.core.exceptions.ValidacaoException;
import br.com.treinaweb.ediaristas.core.models.Diaria;

@Component
public class PagamentoValidator {

    public void validar(Diaria diaria) {
        validarStatus(diaria);
    }

    private void validarStatus(Diaria diaria) {
        if (!diaria.isSemPagamento()) {
            var mensagem = "diária deve estar com o status SEM PAGAMENTO";
            var fieldError = new FieldError(diaria.getClass().getName(), "status", diaria.getStatus(), false, null, null, mensagem);

            throw new ValidacaoException(mensagem, fieldError);
        }
    }

}
