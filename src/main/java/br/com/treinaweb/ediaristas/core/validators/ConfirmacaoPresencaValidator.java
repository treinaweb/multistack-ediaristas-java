package br.com.treinaweb.ediaristas.core.validators;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.core.exceptions.ValidacaoException;
import br.com.treinaweb.ediaristas.core.models.Diaria;

@Component
public class ConfirmacaoPresencaValidator {

    public void validar(Diaria diaria) {
        validarStatus(diaria);
    }

    private void validarStatus(Diaria diaria) {
        if (!diaria.isConfirmado()) {
            var mensagem = "Diária não está com o status CONFIRMADO";
            var fieldError = new FieldError(diaria.getClass().getName(), "status", diaria.getStatus(), false, null, null, mensagem);
            throw new ValidacaoException(mensagem, fieldError);
        }

        validarDataAtendimento(diaria);
    }

    private void validarDataAtendimento(Diaria diaria) {
        var hoje = LocalDateTime.now();
        var dataAtendimento = diaria.getDataAtendimento();

        if (dataAtendimento.isAfter(hoje)) {
            var mensagem = "A data de atendimento da diária deve estar no passado";
            var fieldError = new FieldError(diaria.getClass().getName(), "dataAtendimento", diaria.getDataAtendimento(), false, null, null, mensagem);
            throw new ValidacaoException(mensagem, fieldError);
        }

        validarDiarista(diaria);
    }

    private void validarDiarista(Diaria diaria) {
        if (diaria.getDiarista() == null) {
            var mensagem = "A diária não possui diarista selecionado";
            var fieldError = new FieldError(diaria.getClass().getName(), "diarista", diaria.getDiarista(), false, null, null, mensagem);
            throw new ValidacaoException(mensagem, fieldError);
        }
    }

}
