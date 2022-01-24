package br.com.treinaweb.ediaristas.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.responses.OportunidadeResponse;
import br.com.treinaweb.ediaristas.api.mappers.ApiOportunidadeMapper;
import br.com.treinaweb.ediaristas.core.repositories.DiariaRepository;
import br.com.treinaweb.ediaristas.core.utils.SecurityUtils;

@Service
public class ApiOportunidadeService {

    @Autowired
    private DiariaRepository repository;

    @Autowired
    private ApiOportunidadeMapper mapper;

    @Autowired
    private SecurityUtils securityUtils;

    public List<OportunidadeResponse> buscarOportunidades() {
        var usuarioLogado = securityUtils.getUsuarioLogado();
        var cidades = usuarioLogado.getCidadesAtendidas()
            .stream()
            .map(cidade -> cidade.getCodigoIbge())
            .toList();
        return repository.findOportunidades(cidades, usuarioLogado)
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

}
