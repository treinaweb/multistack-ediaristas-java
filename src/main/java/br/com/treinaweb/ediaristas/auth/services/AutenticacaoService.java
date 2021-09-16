package br.com.treinaweb.ediaristas.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.auth.models.UsuarioAutenticado;
import br.com.treinaweb.ediaristas.core.repositories.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var mensagem = String.format("Usuário com email %s não encontrado", email);

        return repository.findByEmail(email)
            .map(UsuarioAutenticado::new)
            .orElseThrow(() -> new UsernameNotFoundException(mensagem));
    }
    
}
