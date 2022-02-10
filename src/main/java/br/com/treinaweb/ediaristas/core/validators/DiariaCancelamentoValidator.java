package br.com.treinaweb.ediaristas.core.validators;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.core.exceptions.ValidacaoException;
import br.com.treinaweb.ediaristas.core.models.Diaria;

@Component
public class DiariaCancelamentoValidator {

    public void validar(Diaria diaria) {
        validarStatus(diaria);
    }

    private void validarStatus(Diaria diaria) {
        var isNotPagaOrNotConfirmada = !(diaria.isPago() || diaria.isConfirmado());
        if (isNotPagaOrNotConfirmada) {
            var mensagem = "Diária a ser cancelada deve estar com o status PAGO ou CONFIRMADO";
            var fieldError = new FieldError(diaria.getClass().getName(), "status", diaria.getStatus(), false, null, null, mensagem);
            throw new ValidacaoException(mensagem, fieldError);
        }

        validarDataAtendimento(diaria);
    }

    private void validarDataAtendimento(Diaria diaria) {
        var hoje = LocalDateTime.now();
        var isDataAtendimentoNoPassado = diaria.getDataAtendimento().isBefore(hoje);
        if (isDataAtendimentoNoPassado) {
            var mensagem = "Não é mais possivel cancelar a diária.";
            var fieldError = new FieldError(diaria.getClass().getName(), "dataAtendimento", diaria.getDataAtendimento(), false, null, null, mensagem);
            throw new ValidacaoException(mensagem, fieldError);
        }
    }

}
