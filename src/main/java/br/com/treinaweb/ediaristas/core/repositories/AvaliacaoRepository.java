package br.com.treinaweb.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.ediaristas.core.models.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

}
