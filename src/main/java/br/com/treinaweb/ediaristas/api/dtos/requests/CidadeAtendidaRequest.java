package br.com.treinaweb.ediaristas.api.dtos.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadeAtendidaRequest {

    @NotNull
    @NotEmpty
    private String cidade;

    @NotNull
    @NotEmpty
    private String codigoIbge;

}
