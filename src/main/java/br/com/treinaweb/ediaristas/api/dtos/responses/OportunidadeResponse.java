package br.com.treinaweb.ediaristas.api.dtos.responses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonNaming(SnakeCaseStrategy.class)
public class OportunidadeResponse extends DiariaResponse {

    private List<AvaliacaoResponse> avaliacoesCliente;

    public OportunidadeResponse() {
        avaliacoesCliente = new ArrayList<>();
        avaliacoesCliente.add(AvaliacaoResponse.builder()
            .descricao("Muito bom cliente!")
            .nota(5.0)
            .nomeAvaliador("João")
            .fotoAvaliador(null)
            .build()
        );
        avaliacoesCliente.add(AvaliacaoResponse.builder()
            .descricao("Ótimo cliente!")
            .nota(4.5)
            .nomeAvaliador("Pedro")
            .fotoAvaliador(null)
            .build()
        );
    }

}
