package br.com.treinaweb.ediaristas.api.dtos.responses;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonNaming(SnakeCaseStrategy.class)
public class OportunidadeResponse extends DiariaResponse {

    private List<AvaliacaoResponse> avaliacoesCliente;

}
