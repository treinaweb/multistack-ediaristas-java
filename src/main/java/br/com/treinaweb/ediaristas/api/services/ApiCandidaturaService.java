package br.com.treinaweb.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.repositories.DiariaRepository;
import br.com.treinaweb.ediaristas.core.utils.SecurityUtils;

@Service
public class ApiCandidaturaService {

    @Autowired
    private DiariaRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    public MensagemResponse candidatar(Long id) {
        var diaria = buscarDiariaPorId(id);
        var usuarioLogado = securityUtils.getUsuarioLogado();
        diaria.getCandidatos().add(usuarioLogado);
        repository.save(diaria);
        return new MensagemResponse("Candidatura realizada com sucesso!");
    }

    private Diaria buscarDiariaPorId(Long id) {
        var mensagem = String.format("Diária com id %d não encontrada", id);
        return repository.findById(id)
            .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem));
    }

}
