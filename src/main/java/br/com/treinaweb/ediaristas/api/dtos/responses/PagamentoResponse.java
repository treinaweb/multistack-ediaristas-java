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
public class PagamentoResponse {

    private Long id;

    private Integer status;

    private BigDecimal valor;

    private BigDecimal valorDeposito;

    private LocalDateTime createdAt;

}
