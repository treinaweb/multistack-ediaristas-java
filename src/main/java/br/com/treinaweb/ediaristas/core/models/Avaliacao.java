package br.com.treinaweb.ediaristas.core.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Avaliacao {

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
