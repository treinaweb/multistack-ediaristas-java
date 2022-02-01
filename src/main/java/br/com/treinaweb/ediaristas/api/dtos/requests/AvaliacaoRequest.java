package br.com.treinaweb.ediaristas.api.dtos.requests;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class AvaliacaoRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String descricao;

    @NotNull
    @PositiveOrZero
    @Max(value = 5)
    private Double nota;

}
