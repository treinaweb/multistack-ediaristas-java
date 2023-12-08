package br.com.treinaweb.ediaristas.core.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Avaliacao extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Double nota;

    @Column(nullable = false)
    private Boolean visibilidade;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private Diaria diaria;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = true)
    private Usuario avaliador;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private Usuario avaliado;

}
