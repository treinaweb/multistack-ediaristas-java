package br.com.treinaweb.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.ediaristas.core.models.Avaliacao;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.models.Usuario;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    boolean existsByDiariaAndAvaliador(Diaria diaria, Usuario avaliador);

}
