package br.com.treinaweb.ediaristas.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.ediaristas.core.enums.DiariaStatus;
import br.com.treinaweb.ediaristas.core.models.Pagamento;
import br.com.treinaweb.ediaristas.core.models.Usuario;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByDiariaDiaristaAndDiariaStatusIn(Usuario diarista, List<DiariaStatus> status);

}
