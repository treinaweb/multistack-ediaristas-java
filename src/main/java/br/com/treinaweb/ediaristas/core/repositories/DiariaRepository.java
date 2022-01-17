package br.com.treinaweb.ediaristas.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.models.Usuario;

public interface DiariaRepository extends JpaRepository<Diaria, Long> {

    List<Diaria> findByCliente(Usuario cliente);

    List<Diaria> findByDiarista(Usuario diarista);

}
