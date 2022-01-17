package br.com.treinaweb.ediaristas.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadeAtendidaResponse {

    private Long id;
    private String cidade;
    private String estado;
    private String codigoIbge;

}
