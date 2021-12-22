package br.com.treinaweb.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.responses.UsuarioResponse;
import br.com.treinaweb.ediaristas.api.mappers.ApiUsuarioMapper;
import br.com.treinaweb.ediaristas.core.repositories.UsuarioRepository;

@Service
public class ApiMeService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ApiUsuarioMapper usuarioMapper;

    public UsuarioResponse obterUsuarioLogado() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        var usuarioLogado = usuarioRepository.findByEmail(email).get();
        return usuarioMapper.toResponse(usuarioLogado);
    }

}
