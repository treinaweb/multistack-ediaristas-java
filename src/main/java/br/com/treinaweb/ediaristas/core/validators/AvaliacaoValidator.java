package br.com.treinaweb.ediaristas.core.validators;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.core.exceptions.ValidacaoException;
import br.com.treinaweb.ediaristas.core.models.Avaliacao;
import br.com.treinaweb.ediaristas.core.repositories.AvaliacaoRepository;

@Component
public class AvaliacaoValidator {

    @Autowired
    private AvaliacaoRepository repository;

    public void validar(Avaliacao model) {
        validarDiariaStatus(model);
    }

    private void validarDiariaStatus(Avaliacao model) {
        if (!model.getDiaria().isConcluido()) {
            var mensagem = "Diária não está com o status CONCLUIDO";
            var fieldError = new FieldError(model.getClass().getName(), "diaria", null, false, null, null, mensagem);
            throw new ValidacaoException(mensagem, fieldError);
        }

        validarDiariaDataAtendimento(model);
    }

    private void validarDiariaDataAtendimento(Avaliacao model) {
        var hoje = LocalDateTime.now();
        var diariaDataAtendimento = model.getDiaria().getDataAtendimento();

        if (diariaDataAtendimento.isAfter(hoje)) {
            var mensagem = "Diária está com a data de atendimento no futuro";
            var fieldError = new FieldError(model.getClass().getName(), "diaria", null, false, null, null, mensagem);
            throw new ValidacaoException(mensagem, fieldError);
        }

        validarAvaliador(model);
    }

    private void validarAvaliador(Avaliacao model) {
        var diaria = model.getDiaria();
        var avaliador = model.getAvaliador();

        if (repository.existsByDiariaAndAvaliador(diaria, avaliador)) {
            var mensagem = "O usuário já avaliou esta diária";
            var fieldError = new FieldError(model.getClass().getName(), "avaliador", null, false, null, null, mensagem);
            throw new ValidacaoException(mensagem, fieldError);
        }
    }

}
