package br.com.treinaweb.ediaristas.core.specifications;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.criteria.JoinType;

import org.springframework.data.jpa.domain.Specification;

import br.com.treinaweb.ediaristas.core.enums.DiariaStatus;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.models.Diaria_;
import br.com.treinaweb.ediaristas.core.models.Usuario_;

public class DiariaSpecifications {

    public static Specification<Diaria> comNumeroCandidatosIgualA(int numeroCandidatos) {
        return (root, query, criteriaBuilder) -> {
            var quantidadeCandidatos = criteriaBuilder.size(root.get(Diaria_.candidatos));
            return criteriaBuilder.equal(quantidadeCandidatos, numeroCandidatos);
        };
    }

    public static Specification<Diaria> comNumeroCandidatosMenorQue(int numeroCandidatos) {
        return (root, query, criteriaBuilder) -> {
            var quantidadeCandidatos = criteriaBuilder.size(root.get(Diaria_.candidatos));
            return criteriaBuilder.lessThan(quantidadeCandidatos, numeroCandidatos);
        };
    }

    public static Specification<Diaria> comNumeroCandidatosMaiorOuIgualA(int numeroCandidatos) {
        return (root, query, criteriaBuilder) -> {
            var quantidadeCandidatos = criteriaBuilder.size(root.get(Diaria_.candidatos));
            return criteriaBuilder.greaterThanOrEqualTo(quantidadeCandidatos, numeroCandidatos);
        };
    }

    public static Specification<Diaria> isPago() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Diaria_.status), DiariaStatus.PAGO);
        };
    }

    public static Specification<Diaria> isSemPagamento() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Diaria_.status), DiariaStatus.SEM_PAGAMENTO);
        };
    }

    public static Specification<Diaria> semDiarista() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isNull(root.get(Diaria_.diarista));
        };
    }

    public static Specification<Diaria> comMais24HorasDesdeCriacao() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(
                root.get(Diaria_.createdAt),
                LocalDateTime.now().minusHours(24)
            );
        };
    }

    public static Specification<Diaria> semCandidatos() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isEmpty(root.get(Diaria_.candidatos));
        };
    }

    public static Specification<Diaria> comMenos24HorasParaAtendimento() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThan(
                root.get(Diaria_.dataAtendimento),
                LocalDateTime.now().plusHours(24)
            );
        };
    }

    public static Specification<Diaria> clienteNomeCompletoContem(String valorBusca) {
        return (root, query, criteriaBuilder) -> {
            if (valorBusca == null || valorBusca.isEmpty()) {
                return criteriaBuilder.and();
            }
            var cliente = root.join(Diaria_.cliente, JoinType.LEFT);
            var nomeCompletoMinusculo = criteriaBuilder.lower(cliente.get(Usuario_.nomeCompleto));
            return criteriaBuilder.like(nomeCompletoMinusculo, "%" + valorBusca.toLowerCase() + "%");
        };
    }

    public static Specification<Diaria> statusIn(List<DiariaStatus> status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null || status.isEmpty()) {
                return criteriaBuilder.and();
            }
            return root.get(Diaria_.status).in(status);
        };
    }

}
