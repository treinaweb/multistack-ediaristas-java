package br.com.treinaweb.ediaristas.api.dtos.responses;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class DiaristaLocalidadesPagedResponse {

    private List<DiaristaLocalidadeResponse> diaristas;

    private Long quantidadeDiaristas;

    public DiaristaLocalidadesPagedResponse(
        List<DiaristaLocalidadeResponse> diaristas, Integer tamanhoPagina, Long totalElementos
    ) {
        this.diaristas = diaristas;
        this.quantidadeDiaristas = totalElementos > tamanhoPagina ? totalElementos - tamanhoPagina : 0;
    }

}
