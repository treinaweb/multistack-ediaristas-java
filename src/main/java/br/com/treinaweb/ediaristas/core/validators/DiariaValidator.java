package br.com.treinaweb.ediaristas.core.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.core.exceptions.ValidacaoException;
import br.com.treinaweb.ediaristas.core.models.Diaria;

@Component
public class DiariaValidator {

    public void validar(Diaria diaria) {
        validarHoraTermino(diaria);
    }

    private void validarHoraTermino(Diaria diaria) {
        var dataAtendimento = diaria.getDataAtendimento();
        var tempoAtendimento = diaria.getTempoAtendimento();
        var dataTermino = dataAtendimento.plusHours(tempoAtendimento);
        var dataLimite = dataAtendimento.withHour(22).withMinute(0).withSecond(0);

        if (dataTermino.isAfter(dataLimite)) {
            var mensagem = "não pode ser depois das 22";
            var fieldError = new FieldError(diaria.getClass().getName(), "dataAtendimento", diaria.getDataAtendimento(), false, null, null, mensagem);

            throw new ValidacaoException(mensagem, fieldError);
        }

        validarTempoAtendimento(diaria);
    }

    private void validarTempoAtendimento(Diaria diaria) {
        var tempoAtendimento = diaria.getTempoAtendimento();
        var tempoTotal = calcularTempoTotal(diaria);

        if (tempoAtendimento != tempoTotal) {
            var mensagem = "valores não correspondem";
            var fieldError = new FieldError(diaria.getClass().getName(), "tempoAtendimento", diaria.getTempoAtendimento(), false, null, null, mensagem);

            throw new ValidacaoException(mensagem, fieldError);
        }
    }

    private Integer calcularTempoTotal(Diaria diaria) {
        var servico = diaria.getServico();

        Integer tempoTotal = 0;
        tempoTotal += diaria.getQuantidadeQuartos() * servico.getHorasQuarto();
        tempoTotal += diaria.getQuantidadeSalas() * servico.getHorasSala();
        tempoTotal += diaria.getQuantidadeCozinhas() * servico.getHorasCozinha();
        tempoTotal += diaria.getQuantidadeBanheiros() * servico.getHorasBanheiro();
        tempoTotal += diaria.getQuantidadeQuintais() * servico.getHorasQuintal();
        tempoTotal += diaria.getQuantidadeOutros() * servico.getHorasQuarto();

        return tempoTotal;
    }

}
