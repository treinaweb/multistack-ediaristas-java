package br.com.treinaweb.ediaristas.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.ediaristas.core.models.Foto;

public interface FotoRepository extends JpaRepository<Foto, Long> {

    Optional<Foto> findByFilename(String filename);

}
