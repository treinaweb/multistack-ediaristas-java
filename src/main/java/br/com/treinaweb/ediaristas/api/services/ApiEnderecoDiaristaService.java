package br.com.treinaweb.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.requests.EnderecoDiaristaRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.EnderecoDiaristaResponse;
import br.com.treinaweb.ediaristas.api.mappers.ApiEnderecoDiaristaMapper;
import br.com.treinaweb.ediaristas.core.repositories.UsuarioRepository;
import br.com.treinaweb.ediaristas.core.utils.SecurityUtils;

@Service
public class ApiEnderecoDiaristaService {

    @Autowired
    private ApiEnderecoDiaristaMapper mapper;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    public EnderecoDiaristaResponse alterarEndereco(EnderecoDiaristaRequest request) {
        var usuarioLogado = securityUtils.getUsuarioLogado();

        var endereco = mapper.toModel(request);
        usuarioLogado.setEndereco(endereco);

        repository.save(usuarioLogado);

        return mapper.toResponse(usuarioLogado.getEndereco());
    }

}
