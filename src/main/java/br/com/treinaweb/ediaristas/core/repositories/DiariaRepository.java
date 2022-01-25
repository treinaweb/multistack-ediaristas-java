package br.com.treinaweb.ediaristas.core.repositories;

import static br.com.treinaweb.ediaristas.core.specifications.DiariaSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.models.Usuario;

public interface DiariaRepository extends
    JpaRepository<Diaria, Long>,
    JpaSpecificationExecutor<Diaria>
{

    List<Diaria> findByCliente(Usuario cliente);

    List<Diaria> findByDiarista(Usuario diarista);

    @Query(
        """
        SELECT
            d
        FROM
            Diaria d
        WHERE
            d.status = br.com.treinaweb.ediaristas.core.enums.DiariaStatus.PAGO
        AND
            d.diarista IS NULL
        AND
            d.codigoIbge IN :cidades
        AND
            :candidato NOT MEMBER OF d.candidatos
        AND
            SIZE(d.candidatos) < 3
        """
    )
    List<Diaria> findOportunidades(List<String> cidades, Usuario candidato);

    default List<Diaria> getAptasParaSelecaoDeDiarista() {
        return this.findAll(
            where(
                isPago()
                .and(semDiarista())
                .and(comNumeroCandidatosIagualA(3))
            ).or(
                isPago()
                .and(semDiarista())
                .and(comMais24HorasDesdeCriacao())
                .and(comNumeroCandidatosMenorQue(3))
                .and(comNumeroCandidatosMaiorIgualA(1))
            )
        );
    }

}
