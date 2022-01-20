package br.com.treinaweb.ediaristas.core.validators;

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
    }

}
