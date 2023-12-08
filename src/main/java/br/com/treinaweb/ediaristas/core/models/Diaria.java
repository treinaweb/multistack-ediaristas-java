package br.com.treinaweb.ediaristas.core.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import br.com.treinaweb.ediaristas.core.enums.DiariaStatus;
import lombok.AllArgsConstructor;
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
public class Diaria extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(name = "data_atendimento", nullable = false)
    private LocalDateTime dataAtendimento;

    @Column(name = "tempo_atendimento", nullable = false)
    private Integer tempoAtendimento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DiariaStatus status;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(name = "valor_comissao", nullable = false)
    private BigDecimal valorComissao;

    @Column(nullable = false, length = 60)
    private String logradouro;

    @Column(nullable = false, length = 10)
    private String numero;

    @Column(nullable = false, length = 30)
    private String bairro;

    @Column(nullable = true, length = 100)
    private String complemento;

    @Column(nullable = false, length = 30)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(nullable = false)
    private String codigoIbge;

    @Column(name = "quantidade_quartos", nullable = false)
    private Integer quantidadeQuartos;

    @Column(name = "quantidade_salas", nullable = false)
    private Integer quantidadeSalas;

    @Column(name = "quantidade_cozinhas", nullable = false)
    private Integer quantidadeCozinhas;

    @Column(name = "quantidade_banheiros", nullable = false)
    private Integer quantidadeBanheiros;

    @Column(name = "quantidade_quintais", nullable = false)
    private Integer quantidadeQuintais;

    @Column(name = "quantidade_outros", nullable = false)
    private Integer quantidadeOutros;

    @Column(nullable = true)
    private String observacoes;

    @Column(name = "motivo_cancelamento" ,nullable = true)
    private String motivoCancelamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "diarista_id", nullable = true)
    private Usuario diarista;

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;

    @ManyToMany
    @JoinTable(
        name = "diaria_candidato",
        joinColumns = @JoinColumn(name = "diaria_id"),
        inverseJoinColumns = @JoinColumn(name = "candidato_id")
    )
    private List<Usuario> candidatos;

    @OneToMany(mappedBy = "diaria")
    private List<Pagamento> pagamentos;

    public Boolean isSemPagamento() {
        return status.equals(DiariaStatus.SEM_PAGAMENTO);
    }

    public Boolean isPago() {
        return status.equals(DiariaStatus.PAGO);
    }

    public Boolean isConfirmado() {
        return status.equals(DiariaStatus.CONFIRMADO);
    }

    public Boolean isConcluido() {
        return status.equals(DiariaStatus.CONCLUIDO);
    }

}
