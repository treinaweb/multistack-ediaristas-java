package br.com.treinaweb.ediaristas.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.responses.ServicoResponse;
import br.com.treinaweb.ediaristas.api.mappers.ApiServicoMapper;
import br.com.treinaweb.ediaristas.core.models.Servico;
import br.com.treinaweb.ediaristas.core.repositories.ServicoRepository;

@Service
public class ApiServicoService {

    @Autowired
    private ServicoRepository repository;

    @Autowired
    private ApiServicoMapper mapper;

    public List<ServicoResponse> buscarTodos() {
        var servicoSort = Sort.sort(Servico.class);
        var ordenacao = servicoSort.by(Servico::getPosicao).ascending();

        return repository.findAll(ordenacao)
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

}
