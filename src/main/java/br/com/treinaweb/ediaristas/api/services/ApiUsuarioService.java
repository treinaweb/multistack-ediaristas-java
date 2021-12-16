package br.com.treinaweb.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.requests.UsuarioRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.UsuarioResponse;
import br.com.treinaweb.ediaristas.api.mappers.ApiUsuarioMapper;
import br.com.treinaweb.ediaristas.core.repositories.UsuarioRepository;

@Service
public class ApiUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ApiUsuarioMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponse cadastrar(UsuarioRequest request) {
        var usuarioParaCadastrar = mapper.toModel(request);

        var senhaEnctiptada = passwordEncoder.encode(usuarioParaCadastrar.getSenha());
        usuarioParaCadastrar.setSenha(senhaEnctiptada);

        var usuarioCadastrado = repository.save(usuarioParaCadastrar);

        return mapper.toResponse(usuarioCadastrado);
    }

}
