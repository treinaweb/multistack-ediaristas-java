package br.com.treinaweb.ediaristas.core.validators;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.core.exceptions.ValidacaoException;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.utils.SecurityUtils;

@Component
public class CandidaturaValidator {

    @Autowired
    private SecurityUtils securityUtils;

    public void validar(Diaria diaria) {
        validarEnderecoCandidato(diaria);
    }

    private void validarEnderecoCandidato(Diaria diaria) {
        var candidato = securityUtils.getUsuarioLogado();

        if (candidato.getEndereco() == null) {
            var mensagem = "Diarista deve ter o endereço cadastrado";
            var fieldError = new FieldError(diaria.getClass().getName(), "candidatos", null, false, null, null, mensagem);
            throw new ValidacaoException(mensagem, fieldError);
        }

        validarDuplicidadeCandidato(diaria);
    }

    private void validarDuplicidadeCandidato(Diaria diaria) {
        var candidato = securityUtils.getUsuarioLogado();
        var candidatos = diaria.getCandidatos();

        if (candidatos.contains(candidato)) {
            var mensagem = "Diarista já se candidatou para essa diária";
            var fieldError = new FieldError(diaria.getClass().getName(), "candidatos", null, false, null, null, mensagem);
            throw new ValidacaoException(mensagem, fieldError);
        }

        validarQuantidadeCandidatos(diaria);
    }

    private void validarQuantidadeCandidatos(Diaria diaria) {
        var candidatos = diaria.getCandidatos();

        if (candidatos.size() >= 3) {
            var mensagem = "Diária já possui o número máximo de candidatos";
            var fieldError = new FieldError(diaria.getClass().getName(), "candidatos", null, false, null, null, mensagem);
            throw new ValidacaoException(mensagem, fieldError);
        }

        validarStatusDiaria(diaria);
    }

    private void validarStatusDiaria(Diaria diaria) {
        if (!diaria.isPago()) {
            var mensagem = "Diária não está com o status PAGO";
            var fieldError = new FieldError(diaria.getClass().getName(), "status", null, false, null, null, mensagem);
            throw new ValidacaoException(mensagem, fieldError);
        }

        validarDataAtendimentoDiaria(diaria);
    }

    private void validarDataAtendimentoDiaria(Diaria diaria) {
        var hoje = LocalDateTime.now();
        var dataAtendimento = diaria.getDataAtendimento();

        if (hoje.isAfter(dataAtendimento)) {
            var mensagem = "Diária com data de atendimento no passado";
            var fieldError = new FieldError(diaria.getClass().getName(), "dataAtendimento", null, false, null, null, mensagem);
            throw new ValidacaoException(mensagem, fieldError);
        }
    }

}
