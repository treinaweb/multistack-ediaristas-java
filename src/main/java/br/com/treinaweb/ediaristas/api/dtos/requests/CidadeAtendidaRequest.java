package br.com.treinaweb.ediaristas.api.dtos.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class CidadeAtendidaRequest {

    @NotNull
    @NotEmpty
    private String cidade;

    @NotNull
    @NotEmpty
    private String codigoIbge;

}
