package br.com.treinaweb.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.ediaristas.core.models.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    
}
