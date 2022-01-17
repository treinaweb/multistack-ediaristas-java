package br.com.treinaweb.ediaristas.api.dtos.requests;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadesAtendidasRequest {

    private List<CidadeAtendidaRequest> cidades;

}
