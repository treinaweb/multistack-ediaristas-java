package br.com.treinaweb.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.ediaristas.core.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
