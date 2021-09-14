package br.com.treinaweb.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.core.enums.TipoUsuario;
import br.com.treinaweb.ediaristas.core.exceptions.UsuarioNaoEncontradoException;
import br.com.treinaweb.ediaristas.core.models.Usuario;
import br.com.treinaweb.ediaristas.core.repositories.UsuarioRepository;
import br.com.treinaweb.ediaristas.web.dtos.UsuarioCadastroForm;
import br.com.treinaweb.ediaristas.web.mappers.WebUsuarioMapper;

@Service
public class WebUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private WebUsuarioMapper mapper;

    public List<Usuario> buscarTodos() {
        return repository.findAll();
    }

    public Usuario cadastrar(UsuarioCadastroForm form) {
        var model = mapper.toModel(form);

        model.setTipoUsuario(TipoUsuario.ADMIN);

        return repository.save(model);
    }

    public Usuario buscarPorId(Long id) {
        var mensagem = String.format("Usuário com ID %d não encontrado", id);

        return repository.findById(id)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(mensagem));
    }

    public void excluirPorId(Long id) {
        var usuario = buscarPorId(id);

        repository.delete(usuario);
    }
    
}
