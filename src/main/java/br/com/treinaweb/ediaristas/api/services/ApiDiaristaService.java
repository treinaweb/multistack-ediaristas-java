package br.com.treinaweb.ediaristas.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.responses.DiaristaLocalidadeResponse;
import br.com.treinaweb.ediaristas.api.mappers.ApiDiaristaMapper;
import br.com.treinaweb.ediaristas.core.repositories.UsuarioRepository;

@Service
public class ApiDiaristaService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ApiDiaristaMapper mapper;

    public List<DiaristaLocalidadeResponse> buscarDiaristasPorCep() {
        return repository.findAll()
            .stream()
            .map(mapper::toDiaristaLocalidadeResponse)
            .toList();
    }

}
