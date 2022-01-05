package br.com.treinaweb.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.requests.PagamentoRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.ediaristas.core.enums.DiariaStatus;
import br.com.treinaweb.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.repositories.DiariaRepository;
import br.com.treinaweb.ediaristas.core.validators.PagamentoValidator;

@Service
public class ApiDiariaPagamentoService {

    @Autowired
    private DiariaRepository diariaRepository;

    @Autowired
    private PagamentoValidator validator;

    public MensagemResponse pagar(PagamentoRequest request, Long id) {
        var diaria = buscarDiariaPorId(id);

        validator.validar(diaria);

        diaria.setStatus(DiariaStatus.PAGO);
        diariaRepository.save(diaria);

        return new MensagemResponse("Diária paga com sucesso!");
    }

    private Diaria buscarDiariaPorId(Long id) {
        var mensagem = String.format("Diária com id %d não encontrada", id);
        return diariaRepository.findById(id)
            .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem));
    }

}
