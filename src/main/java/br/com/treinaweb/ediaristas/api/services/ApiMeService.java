package br.com.treinaweb.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.responses.UsuarioResponse;
import br.com.treinaweb.ediaristas.api.mappers.ApiUsuarioMapper;
import br.com.treinaweb.ediaristas.core.utils.SecurityUtils;

@Service
public class ApiMeService {

    @Autowired
    private ApiUsuarioMapper usuarioMapper;

    @Autowired
    private SecurityUtils securityUtils;

    public UsuarioResponse obterUsuarioLogado() {
        var usuarioLogado = securityUtils.getUsuarioLogado();
        return usuarioMapper.toResponse(usuarioLogado);
    }

}
