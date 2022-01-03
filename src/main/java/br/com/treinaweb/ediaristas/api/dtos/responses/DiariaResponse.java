package br.com.treinaweb.ediaristas.api.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class DiariaResponse {

    private Long id;

    private Integer status;

    private BigDecimal valorComissao;

    private String motivoCancelamento;

    private String nomeServico;

    private String complemento;

    private LocalDateTime dataAtendimento;

    private Integer tempoAtendimento;

    private BigDecimal preco;

    private String logradouro;

    private String numero;

    private String bairro;

    private String estado;

    private String codigoIbge;

    private Integer quantidadeQuartos;

    private Integer quantidadeSalas;

    private Integer quantidadeCozinhas;

    private Integer quantidadeBanheiros;

    private Integer quantidadeQuintais;

    private Integer quantidadeOutros;

    private String observacoes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long servico;

    private ClienteResponse cliente;

}
