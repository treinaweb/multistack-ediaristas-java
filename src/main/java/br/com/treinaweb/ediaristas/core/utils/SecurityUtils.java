package br.com.treinaweb.ediaristas.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.treinaweb.ediaristas.core.enums.TipoUsuario;
import br.com.treinaweb.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.treinaweb.ediaristas.core.exceptions.UsuarioNaoEncontradoException;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.models.Usuario;
import br.com.treinaweb.ediaristas.core.repositories.DiariaRepository;
import br.com.treinaweb.ediaristas.core.repositories.UsuarioRepository;

@Component
public class SecurityUtils {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DiariaRepository diariaRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Boolean isCliente() {
        var authentication = getAuthentication();
        var tipoCliente = TipoUsuario.CLIENTE.name();
        return authentication.getAuthorities()
            .stream()
            .anyMatch(authority -> authority.getAuthority().equals(tipoCliente));
    }

    public String getEmailUsuarioLogado() {
        return getAuthentication().getName();
    }

    public Usuario getUsuarioLogado() {
        var email = getEmailUsuarioLogado();
        var mensagem = String.format("Usuário com email %s não encontrado", email);
        return usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(mensagem));
    }

    public Boolean isClienteDaDiaria(Long id) {
        var diaria = buscarDiariaPorId(id);
        var usuarioLogado = getUsuarioLogado();

        if (!usuarioLogado.isCliente()) {
            return false;
        }

        return diaria.getCliente().equals(usuarioLogado);
    }

    private Diaria buscarDiariaPorId(Long id) {
        var mensagem = String.format("Diária com id %d não encontrada", id);
        return diariaRepository.findById(id)
            .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem));
    }

}
