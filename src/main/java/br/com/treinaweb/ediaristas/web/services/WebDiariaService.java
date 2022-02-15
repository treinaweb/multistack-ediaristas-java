package br.com.treinaweb.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.core.enums.DiariaStatus;
import br.com.treinaweb.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.repositories.DiariaRepository;
import br.com.treinaweb.ediaristas.core.validators.TransferenciaValidator;

@Service
public class WebDiariaService {

    @Autowired
    private DiariaRepository diariaRepository;

    @Autowired
    private TransferenciaValidator validator;

    public List<Diaria> buscarDarias() {
        return diariaRepository.findAll();
    }

    public void pagar(Long id) {
        var diaria = buscarDiariaPorId(id);
        validator.validar(diaria);
        diaria.setStatus(DiariaStatus.TRANSFERIDO);
        diariaRepository.save(diaria);
    }

    private Diaria buscarDiariaPorId(Long id) {
        var mensagem = String.format("Diária com id %d não encontrada!", id);
        return diariaRepository.findById(id)
            .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem));
    }

}
