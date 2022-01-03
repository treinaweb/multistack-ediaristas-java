package br.com.treinaweb.ediaristas.api.dtos.requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class DiariaRequest {

    @NotNull
    @Future
    private LocalDateTime dataAtendimento;

    @NotNull
    @Positive
    private Integer tempoAtendimento;

    @NotNull
    @Positive
    private BigDecimal preco;

    @NotNull
    @NotEmpty
    @Size(max = 60)
    private String logradouro;

    @NotNull
    @NotEmpty
    @Size(max = 10)
    private String numero;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String bairro;

    @Size(max = 100)
    private String complemento;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String cidade;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 2)
    private String estado;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 8)
    private String cep;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String codigoIbge;

    @NotNull
    @PositiveOrZero
    private Integer quantidadeQuartos;

    @NotNull
    @PositiveOrZero
    private Integer quantidadeSalas;

    @NotNull
    @PositiveOrZero
    private Integer quantidadeCozinhas;

    @NotNull
    @PositiveOrZero
    private Integer quantidadeBanheiros;

    @NotNull
    @PositiveOrZero
    private Integer quantidadeQuintais;

    @NotNull
    @PositiveOrZero
    private Integer quantidadeOutros;

    @Size(max = 255)
    private String observacoes;

    @NotNull
    @Positive
    private Long servico;

}
