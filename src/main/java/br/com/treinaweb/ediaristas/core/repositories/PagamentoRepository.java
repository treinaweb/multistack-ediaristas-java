package br.com.treinaweb.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.ediaristas.core.models.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
