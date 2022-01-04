package br.com.treinaweb.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.ediaristas.core.models.Diaria;

public interface DiariaRepository extends JpaRepository<Diaria, Long> {

}
