package br.com.treinaweb.ediaristas.api.dtos.requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.treinaweb.ediaristas.core.validators.HoraDepoisDe;
import br.com.treinaweb.ediaristas.core.validators.ServicoExistsById;
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
    @HoraDepoisDe(horaInicio = 6)
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
    @ServicoExistsById
    private Long servico;

}
