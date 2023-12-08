package br.com.treinaweb.ediaristas.core.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import br.com.treinaweb.ediaristas.core.enums.PagamentoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
@Builder
public class Pagamento extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PagamentoStatus status;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(name = "transacao_id", nullable = false)
    private String transacaoId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Diaria diaria;

    public boolean isAceito() {
        return status.equals(PagamentoStatus.ACEITO);
    }

}
